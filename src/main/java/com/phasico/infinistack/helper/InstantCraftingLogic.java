package com.phasico.infinistack.helper;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.inventory.InventoryCrafting;

public class InstantCraftingLogic {

    /**
     * Performs instant crafting of items with large stack size without lag!
     */
    public static boolean instantCraft(InventoryCrafting craftMatrix, IRecipe recipe, InventoryPlayer playerInventory) {
        if (recipe == null) return false;

        ItemStack recipeResult = recipe.getCraftingResult(craftMatrix);
        if (recipeResult == null) return false;

        //For things that cannot stack, just let the vanilla logic handle it.
        if (recipeResult.getMaxStackSize() == 1) return false;

        int maxCraft = calculateMaxCraft(craftMatrix);
        if (maxCraft <= 0) return false;

        ItemStack finalResult = recipeResult.copy();

        long totalAmount = (long) recipeResult.stackSize * maxCraft;

        if (totalAmount > Integer.MAX_VALUE) {
            consumeIngredients(craftMatrix, maxCraft, playerInventory);
            returnBigResult(playerInventory, finalResult, totalAmount);
        } else {
            finalResult.stackSize = (int) totalAmount;
            consumeIngredients(craftMatrix, maxCraft, playerInventory);
            returnResult(playerInventory, finalResult);
        }

        return true;
    }

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

    private static int calculateMaxCraft(InventoryCrafting craftMatrix) {
        int maxCraft = Integer.MAX_VALUE;

        for (int i = 0; i < 9; i++) {
            ItemStack craftItem = craftMatrix.getStackInSlot(i);
            if (craftItem != null) {
                maxCraft = Math.min(maxCraft, craftItem.stackSize);
            }
        }

        return maxCraft == Integer.MAX_VALUE ? 0 : maxCraft;
    }

    private static void consumeIngredients(InventoryCrafting craftMatrix, int craftCount, net.minecraft.entity.player.InventoryPlayer playerInventory) {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = craftMatrix.getStackInSlot(i);
            if (stack != null) {
                if (stack.getItem().hasContainerItem(stack)) {
                    ItemStack containerItem = stack.getItem().getContainerItem(stack);
                    if (containerItem != null) {
                        containerItem.stackSize = craftCount;
                        returnResult(playerInventory, containerItem);
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