package com.phasico.infinistack.mixins;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CraftingManager.class)
public abstract class MixinCraftingManager {

    @Unique
    private static volatile IRecipe lastMatchedRecipe = null;

    @Inject(method = "findMatchingRecipe", at = @At("HEAD"), cancellable = true)
    private void memoizedLookup(InventoryCrafting craftMatrix, World world, CallbackInfoReturnable<ItemStack> cir) {
        if (isRepairShaped(craftMatrix)) {
            return;
        }

        IRecipe memo = lastMatchedRecipe;
        if (memo != null && memo.matches(craftMatrix, world)) {
            cir.setReturnValue(memo.getCraftingResult(craftMatrix));
        }
    }

    @Redirect(
        method = "findMatchingRecipe",
        at = @At(value = "INVOKE",
                 target = "Lnet/minecraft/item/crafting/IRecipe;matches(Lnet/minecraft/inventory/InventoryCrafting;Lnet/minecraft/world/World;)Z")
    )
    private boolean recordMatchedRecipe(IRecipe recipe, InventoryCrafting craftMatrix, World world) {
        boolean matched = recipe.matches(craftMatrix, world);
        if (matched) {
            lastMatchedRecipe = recipe;
        }
        return matched;
    }

    @Unique
    private static boolean isRepairShaped(InventoryCrafting craftMatrix) {
        int filled = 0;
        ItemStack first = null;
        ItemStack second = null;

        for (int i = 0; i < craftMatrix.getSizeInventory(); i++) {
            ItemStack stack = craftMatrix.getStackInSlot(i);
            if (stack != null) {
                if (filled == 0) first = stack;
                if (filled == 1) second = stack;
                filled++;
            }
        }

        return filled == 2 && first.getItem() == second.getItem()
                && first.stackSize == 1 && second.stackSize == 1
                && first.getItem().isDamageable();
    }
}
