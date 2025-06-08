package com.phasico.infinistack.helper;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.world.World;

public class InstantPlayerCraftingLogic {

    /**
     * Performs instant crafting of items with large stack size without lag!
     * This is for the Player's crafting.
     */
    public static boolean instantCraft(InventoryCrafting craftMatrix, SlotCrafting craftingSlot, IRecipe recipe, InventoryPlayer playerInventory, EntityPlayer player) {
        if (recipe == null) return false;

        ItemStack recipeResult = recipe.getCraftingResult(craftMatrix);
        if (recipeResult == null) return false;

        int maxCraft = calculateMaxCraft(craftMatrix);
        if (maxCraft <= 0) return false;

        // Check inventory space BEFORE doing anything
        long inventorySpace = calculateMaxFit(playerInventory, recipeResult);
        if (inventorySpace <= 0) {
            return false; // No space available, don't consume ingredients
        }

        ItemStack finalResult = recipeResult.copy();

        long totalAmount = (long) recipeResult.stackSize * maxCraft;

        if (totalAmount > inventorySpace){
            long newMaxCraft = (inventorySpace / recipeResult.stackSize);
            maxCraft = (int)newMaxCraft;
            totalAmount = (long) recipeResult.stackSize * maxCraft;

            // Double-check that we can still craft something
            if (maxCraft <= 0) {
                return false;
            }
        }

        if (totalAmount > Integer.MAX_VALUE) {
            consumeIngredients(craftMatrix, maxCraft, playerInventory, player.worldObj, player);
            returnBigResult(playerInventory, finalResult, player.worldObj, player, totalAmount);
        } else {
            finalResult.stackSize = (int) totalAmount;
            consumeIngredients(craftMatrix, maxCraft, playerInventory, player.worldObj, player);
            returnResult(playerInventory, finalResult, player.worldObj, player);
        }


        //Achievement & Stuff

        FMLCommonHandler.instance().firePlayerCraftingEvent(player, recipeResult, craftMatrix);
        craftingSlot.onCrafting(recipeResult);

        return true;
    }

    private static long calculateMaxFit(InventoryPlayer inventory, ItemStack result) {
        long total = 0;
        int maxStack = result.getMaxStackSize();


        //0 is result, 1-4 is crafting, 5-8 is armor. 9-44 is main inventory and hotbar.
        for (int i = 0; i < 36; i++) {
            ItemStack slot = inventory.getStackInSlot(i);
            if (slot == null) {
                total += maxStack;
            } else if (inventory.isItemValidForSlot(i, result) &&
                    slot.isItemEqual(result) &&
                    ItemStack.areItemStackTagsEqual(slot, result)) {
                total += (maxStack - slot.stackSize);
            }
        }

        return total;
    }

    private static void returnResult(InventoryPlayer playerInventory, ItemStack result, World world, EntityPlayer player) {
        if (result == null || result.stackSize <= 0) return;

        ItemStack remaining = result.copy();
        int maxStackSize = remaining.getMaxStackSize();

        while (remaining.stackSize > 0) {
            ItemStack toAdd = remaining.copy();
            if (toAdd.stackSize > maxStackSize) {
                toAdd.stackSize = maxStackSize;
                remaining.stackSize -= maxStackSize;
            } else {
                remaining.stackSize = 0;
            }

            if (!playerInventory.addItemStackToInventory(toAdd)) {
                if (!playerInventory.player.worldObj.isRemote) {

                    //No random needed, this is just to gurantee that no item lost happens.
                    EntityItem entityItem = new EntityItem(world, player.posX, player.posY, player.posZ, toAdd);
                    world.spawnEntityInWorld(entityItem);

                    }
            }
        }
    }

    private static void returnBigResult(InventoryPlayer playerInventory, ItemStack result, World world, EntityPlayer player, long returnSize){
        if (result == null || returnSize <= 0) return;

        long size = returnSize;
        ItemStack remaining = result.copy();

        while (size > 0) {

            ItemStack toAdd = remaining.copy();

            if (size > Integer.MAX_VALUE) {
                toAdd.stackSize = Integer.MAX_VALUE;
                size -= Integer.MAX_VALUE;
            } else {
                toAdd.stackSize = (int)size;
                size = 0;
            }

            returnResult(playerInventory, toAdd, world, player);
        }
    }

    private static int calculateMaxCraft(InventoryCrafting craftMatrix) {
        int maxCraft = Integer.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            ItemStack craftItem = craftMatrix.getStackInSlot(i);
            if (craftItem != null) {
                maxCraft = Math.min(maxCraft, craftItem.stackSize);
            }
        }

        return maxCraft == Integer.MAX_VALUE ? 0 : maxCraft;
    }

    private static void consumeIngredients(InventoryCrafting craftMatrix, int craftCount, InventoryPlayer playerInventory, World world, EntityPlayer player) {
        for (int i = 0; i < 4; i++) {
            ItemStack stack = craftMatrix.getStackInSlot(i);
            if (stack != null) {
                if (stack.getItem().hasContainerItem(stack)) {
                    ItemStack containerItem = stack.getItem().getContainerItem(stack);
                    if (containerItem != null) {
                        containerItem.stackSize = craftCount;
                        returnResult(playerInventory, containerItem, world, player);
                    }
                    craftMatrix.setInventorySlotContents(i, null);
                } else {
                    stack.stackSize -= craftCount;
                    if (stack.stackSize <= 0) {
                        craftMatrix.setInventorySlotContents(i, null);
                    }
                }
            }
        }
    }
}