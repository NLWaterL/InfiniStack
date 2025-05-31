package com.phasico.infinistack.helper;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.inventory.InventoryCrafting;

public class InstantCraftingLogic {

    /**
     * Performs instant crafting of items with large stack size without lag!
     */
    public static boolean instantCraft(InventoryCrafting craftMatrix, IRecipe recipe, net.minecraft.entity.player.InventoryPlayer playerInventory) {
        if (recipe == null) return false;

        ItemStack recipeResult = recipe.getCraftingResult(craftMatrix);
        if (recipeResult == null) return false;

        int maxCraft = calculateMaxCraft(craftMatrix, recipe);
        if (maxCraft <= 0) return false;

        ItemStack finalResult = recipeResult.copy();

        long totalAmount = (long) recipeResult.stackSize * maxCraft;

        if (totalAmount > Integer.MAX_VALUE) {
            consumeIngredients(craftMatrix, recipe, maxCraft, playerInventory);
            returnBigResult(playerInventory, finalResult, totalAmount);
        } else {
            finalResult.stackSize = (int) totalAmount;
            consumeIngredients(craftMatrix, recipe, maxCraft, playerInventory);
            returnResult(playerInventory, finalResult);
        }

        return true;
    }

    /**
     * Adds an ItemStack to player inventory, handling stack splitting if needed
     */
    private static void returnResult(InventoryPlayer playerInventory, ItemStack result) {
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
                    playerInventory.player.dropPlayerItemWithRandomChoice(toAdd, false);
                }
            }
        }
    }

    /**
     * Handles super big result with stackSize exceeding the integer size limit.
     */
    private static void returnBigResult(InventoryPlayer playerInventory, ItemStack result, long returnSize){
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

            returnResult(playerInventory, toAdd);
        }
    }

    /**
     * Calculates the maximum number of times a recipe can be crafted
     * based on available ingredients
     */
    private static int calculateMaxCraft(InventoryCrafting craftMatrix, IRecipe recipe) {
        int maxCraft = Integer.MAX_VALUE;

        // Simply check each slot - if it's used in the recipe, it limits our craft count
        for (int i = 0; i < 9; i++) {
            ItemStack craftItem = craftMatrix.getStackInSlot(i);
            if (craftItem != null) {
                // This slot has items, so it limits how many we can craft
                maxCraft = Math.min(maxCraft, craftItem.stackSize);
            }
        }

        return maxCraft == Integer.MAX_VALUE ? 0 : maxCraft;
    }

    /**
     * Consumes ingredients and handles container items (like buckets)
     */
    private static void consumeIngredients(InventoryCrafting craftMatrix, IRecipe recipe, int craftCount, net.minecraft.entity.player.InventoryPlayer playerInventory) {
        // Simple unified approach - consume ingredients from each slot
        for (int i = 0; i < 9; i++) {
            ItemStack stack = craftMatrix.getStackInSlot(i);
            if (stack != null) {
                if (stack.getItem().hasContainerItem(stack)) {
                    // Handle container items (like buckets)
                    ItemStack containerItem = stack.getItem().getContainerItem(stack);
                    if (containerItem != null) {
                        // Container items are returned equal to the number of crafts
                        containerItem.stackSize = craftCount;
                        returnResult(playerInventory, containerItem);
                    }
                    // Remove the original item completely since it was consumed
                    craftMatrix.setInventorySlotContents(i, null);
                } else {
                    // Normal consumption
                    stack.stackSize -= craftCount;
                    if (stack.stackSize <= 0) {
                        craftMatrix.setInventorySlotContents(i, null);
                    }
                }
            }
        }
    }
}