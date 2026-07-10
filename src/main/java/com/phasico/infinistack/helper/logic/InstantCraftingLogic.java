package com.phasico.infinistack.helper.logic;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class InstantCraftingLogic {

    /**
     * Performs instant crafting of items with large stack size without lag!
     * Now only used for hbm book recipes and extreme crafting recipes.
     * @param size The dimension of the crafting matrix. For example, vanilla workbench is 3.
     */
    public static boolean instantCraft(InventoryCrafting craftMatrix, SlotCrafting craftingSlot, IRecipe recipe, EntityPlayer player, int size) {

        if (recipe == null) return false;

        ItemStack recipeResult = recipe.getCraftingResult(craftMatrix);
        if (recipeResult == null) return false;

        int maxCraft = calculateMaxCraft(craftMatrix, size);
        if (maxCraft <= 0) return false;

        // Check inventory space BEFORE doing anything
        long inventorySpace = calculateMaxFit(player.inventory, recipeResult, 0, 36);
        if (inventorySpace <= 0) {
            return false; // No space available, don't consume ingredients
        }

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

        consumeIngredients(craftMatrix, maxCraft, player, size);
        returnResultToPlayer(recipeResult, player, totalAmount);

        //Achievement & Stuff

        FMLCommonHandler.instance().firePlayerCraftingEvent(player, recipeResult, craftMatrix);
        craftingSlot.onCrafting(recipeResult, maxCraft);

        return true;
    }

    public static long calculateMaxFit(IInventory inventory, ItemStack stack, int startIndex, int endIndex) {
        long fit = 0;

        for (int i = startIndex; i < endIndex; i++) {
            ItemStack slotStack = inventory.getStackInSlot(i);

            if (inventory.isItemValidForSlot(i, stack)) {

                if (slotStack == null) {
                    int freeSpace = Math.min(stack.getMaxStackSize(), inventory.getInventoryStackLimit());
                    fit += freeSpace;
                    continue;
                }

                if (stack.isStackable() &&
                        stack.isItemEqual(slotStack) &&
                        ItemStack.areItemStackTagsEqual(stack, slotStack)) {

                    int freeSpace = Math.min(stack.getMaxStackSize(), inventory.getInventoryStackLimit()) - slotStack.stackSize;

                    if (freeSpace > 0) {
                        fit += freeSpace;
                    }
                }
            }
        }

        return fit;
    }

    public static long calculateMaxFit(Container container, ItemStack stack, int startIndex, int endIndex) {
        long fit = 0;
        Slot slot;
        ItemStack slotStack;

        for (int slotIndex = startIndex; slotIndex < endIndex; slotIndex++) {

            slot = (Slot) container.inventorySlots.get(slotIndex);
            slotStack = slot.getStack();

            if (slot.isItemValid(stack)) {

                if (slotStack == null) {
                    int freeSpace = Math.min(stack.getMaxStackSize(), slot.getSlotStackLimit());
                    fit += freeSpace;
                    continue;
                }

                if (stack.isStackable() &&
                        stack.isItemEqual(slotStack) &&
                        ItemStack.areItemStackTagsEqual(stack, slotStack)) {

                    int freeSpace = Math.min(stack.getMaxStackSize(), slot.getSlotStackLimit()) - slotStack.stackSize;

                    if (freeSpace > 0) {
                        fit += freeSpace;
                    }
                }

            }
        }

        return fit;
    }


    public static void returnResultToPlayer(ItemStack result, EntityPlayer player){
        returnResultToPlayer(result, player, result.stackSize);
    }

    public static void returnResultToPlayer(ItemStack result, EntityPlayer player, long count) {
        if (result == null || count <= 0) return;

        int maxStackSize = result.getMaxStackSize();

        while (count > 0) {
            ItemStack toAdd = result.copy();
            toAdd.stackSize = (int) Math.min(count, maxStackSize);
            count -= toAdd.stackSize;

            if (!player.inventory.addItemStackToInventory(toAdd)) {
                player.dropPlayerItemWithRandomChoice(toAdd, false);
            }
        }
    }


    public static long returnResult(Container container, ItemStack result, int startIndex, int endIndex, long size) {
        if (result == null || size <= 0) return size;

        long remaining = size;
        int maxStackSize = result.getMaxStackSize();

        while (remaining > 0) {
            ItemStack batch = result.copy();
            batch.stackSize = (int) Math.min(remaining, maxStackSize);
            int attempted = batch.stackSize;

            container.mergeItemStack(batch, startIndex, endIndex, false);

            remaining -= (attempted - batch.stackSize);

            if (batch.stackSize > 0) {
                break;
            }
        }

        return remaining;
    }

    public static int calculateMaxCraft(InventoryCrafting craftMatrix, int size) {
        int maxCraft = Integer.MAX_VALUE;

        for (int i = 0; i < (size * size); i++) {

            ItemStack craftStack = craftMatrix.getStackInSlot(i);

            if (craftStack != null) {

                if (craftStack.getItem().hasContainerItem(craftStack)) {
                    ItemStack containerStack = craftStack.getItem().getContainerItem(craftStack);
                    if (!craftStack.getItem().doesContainerItemLeaveCraftingGrid(craftStack)
                            && containerStack != null
                            && craftStack.isItemEqual(containerStack)
                            && ItemStack.areItemStackTagsEqual(craftStack, containerStack)
                    ) {
                        continue;
                    }
                }

                maxCraft = Math.min(maxCraft, craftStack.stackSize);

            }
        }

        return maxCraft == Integer.MAX_VALUE ? 0 : maxCraft;
    }

    public static void consumeIngredients(InventoryCrafting craftMatrix, int craftCount, EntityPlayer player, int size) {
        for (int i = 0; i < (size * size); i++) {
            ItemStack stack = craftMatrix.getStackInSlot(i);

            if (stack != null) {

                if (stack.getItem().hasContainerItem(stack)) {

                    ItemStack containerItem = stack.getItem().getContainerItem(stack);

                    if (!stack.getItem().doesContainerItemLeaveCraftingGrid(stack)) {
                        craftMatrix.setInventorySlotContents(i, containerItem);
                        continue;
                    } else {
                        if (containerItem != null) {
                            containerItem.stackSize = craftCount;
                            returnResultToPlayer(containerItem, player);
                        }
                    }
                }

                stack.stackSize -= craftCount;
                if (stack.stackSize <= 0) {
                    craftMatrix.setInventorySlotContents(i, null);
                }

            }
        }
    }

}