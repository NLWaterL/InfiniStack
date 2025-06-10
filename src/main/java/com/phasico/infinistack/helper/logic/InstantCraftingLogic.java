package com.phasico.infinistack.helper.logic;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.world.World;

public class InstantCraftingLogic {

    /**
     * Performs instant crafting of items with large stack size without lag!
     * @param size The dimension of the crafting matrix. For example, vanilla workbench is 3.
     * */
    public static boolean instantCraft(InventoryCrafting craftMatrix, SlotCrafting craftingSlot, IRecipe recipe, InventoryPlayer playerInventory, EntityPlayer player, int size) {

        //TODO: Add compact for Forestry's Worktable (logic is quite different)
        //Wait until I finish other things.

        if (recipe == null) return false;

        ItemStack recipeResult = recipe.getCraftingResult(craftMatrix);
        if (recipeResult == null) return false;

        int maxCraft = calculateMaxCraft(craftMatrix, size);
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
            consumeIngredients(craftMatrix, maxCraft, playerInventory, player, size);
            returnBigResult(playerInventory, finalResult, player, totalAmount);
        } else {
            finalResult.stackSize = (int) totalAmount;
            consumeIngredients(craftMatrix, maxCraft, playerInventory, player, size);
            returnResult(playerInventory, finalResult, player);
        }


        //Achievement & Stuff

        FMLCommonHandler.instance().firePlayerCraftingEvent(player, recipeResult, craftMatrix);
        craftingSlot.onCrafting(recipeResult);

        return true;
    }

    //Each method can be used alone for different purposes. This is more like a util class.
    //You can even use these things for non-crafting logic.
    public static long calculateMaxFit(InventoryPlayer inventory, ItemStack result) {
        long total = 0;
        int maxStack = result.getMaxStackSize();

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

    public static void returnResult(InventoryPlayer playerInventory, ItemStack result, EntityPlayer player) {
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
                player.dropPlayerItemWithRandomChoice(toAdd,false);
            }
        }
    }

    public static void returnBigResult(InventoryPlayer playerInventory, ItemStack result, EntityPlayer player, long returnSize){
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

            returnResult(playerInventory, toAdd, player);
        }
    }

    public static int calculateMaxCraft(InventoryCrafting craftMatrix, int size) {
        int maxCraft = Integer.MAX_VALUE;

        for (int i = 0; i < (size * size); i++) {
            ItemStack craftItem = craftMatrix.getStackInSlot(i);
            if (craftItem != null) {
                maxCraft = Math.min(maxCraft, craftItem.stackSize);
            }
        }

        return maxCraft == Integer.MAX_VALUE ? 0 : maxCraft;
    }

    public static void consumeIngredients(InventoryCrafting craftMatrix, int craftCount, InventoryPlayer playerInventory, EntityPlayer player, int size) {
        for (int i = 0; i < (size * size); i++) {
            ItemStack stack = craftMatrix.getStackInSlot(i);
            if (stack != null) {
                if (stack.getItem().hasContainerItem(stack)) {
                    ItemStack containerItem = stack.getItem().getContainerItem(stack);
                    if (containerItem != null) {
                        containerItem.stackSize = craftCount;
                        returnResult(playerInventory, containerItem, player);
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