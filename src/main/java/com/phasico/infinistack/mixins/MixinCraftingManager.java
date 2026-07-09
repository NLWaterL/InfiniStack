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

/**
 * Memoizes CraftingManager.findMatchingRecipe with an AE2-style single-entry recipe cache
 * (appeng.util.Platform.lastUsedRecipe). Nearly every crafting container's onCraftMatrixChanged
 * funnels through this method, so this one change makes the single post-craft fire
 * (MixinSlotCrafting) O(1) for every supported table - and any unsupported mod's table too -
 * without overwriting each mod's own change-handler logic.
 *
 * A single static entry is enough: the hot loop (one shift-click's retry-driven per-unit crafts,
 * MixinContainer.limitedRetrySlotClick) runs start-to-finish on the server thread while handling
 * one click packet, so nothing can evict the entry mid-loop. Eviction between clicks (another
 * player crafting a different recipe) costs exactly one vanilla full scan - the vanilla baseline,
 * not a freeze. The client and integrated-server threads both touch the field, but they work on
 * the same grid contents and every use is revalidated with matches(), so a stale entry only costs
 * a miss, never a wrong result.
 *
 * Implemented as inject + redirect rather than @Overwrite so it composes with other coremods that
 * may patch this hot method in a GTNH environment.
 */
@Mixin(CraftingManager.class)
public abstract class MixinCraftingManager {

    @Unique
    private static volatile IRecipe lastMatchedRecipe = null;

    // Memo shortcut. Repair-shaped grids must fall through: vanilla's 2-damageable-items repair
    // branch takes priority over the recipe list and computes its result from the tools' exact
    // damage, so it can never be served from the cache.
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

    // On a real scan, remember which recipe won so the next lookup is a memo hit. findMatchingRecipe
    // returns the result ItemStack, not the IRecipe, so the recipe has to be captured here at the
    // matches() call site.
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

    // Mirrors the shape test at the top of vanilla findMatchingRecipe exactly (two lone damageable
    // items of the same type) - if this says repair, vanilla's repair branch would run.
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
