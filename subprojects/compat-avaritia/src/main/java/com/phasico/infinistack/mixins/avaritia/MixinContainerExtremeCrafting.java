package com.phasico.infinistack.mixins.avaritia;

import com.phasico.infinistack.helper.FixedCraftingContainer;
import fox.spiteful.avaritia.crafting.ExtremeCraftingManager;
import fox.spiteful.avaritia.gui.ContainerExtremeCrafting;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

// The 9x9 dire table crafts from ExtremeCraftingManager's own recipe list, NOT the vanilla
// CraftingManager - so MixinCraftingManager's memo never sees extreme recipes and the table
// carries its own single-entry memory here, applied where its func_75130_a does the lookup. The
// grid (InventoryDireCrafting) fires the change event itself - silenced by
// MixinInventoryDireCrafting while MixinSlotCrafting batches a craft to one fire.
@Mixin(ContainerExtremeCrafting.class)
@Pseudo
public abstract class MixinContainerExtremeCrafting implements FixedCraftingContainer {

    @Unique
    private IRecipe lastExtremeRecipe = null;

    // Memoizes the extreme recipe search exactly like MixinCraftingManager does for vanilla:
    // repair-shaped grids fall through to the original (its repair result depends on the tools'
    // exact damage), everything else is served by revalidating the last matched recipe before
    // falling back to a recording scan of the same list the original walks.
    @Redirect(
            method = "func_75130_a",
            at = @At(value = "INVOKE",
                     target = "Lfox/spiteful/avaritia/crafting/ExtremeCraftingManager;findMatchingRecipe(Lnet/minecraft/inventory/InventoryCrafting;Lnet/minecraft/world/World;)Lnet/minecraft/item/ItemStack;"),
            remap = false
    )
    private ItemStack memoizedExtremeLookup(ExtremeCraftingManager manager, InventoryCrafting matrix, World world) {
        if (isRepairShaped(matrix)) {
            return manager.findMatchingRecipe(matrix, world);
        }

        IRecipe memo = lastExtremeRecipe;
        if (memo != null && memo.matches(matrix, world)) {
            return memo.getCraftingResult(matrix);
        }

        for (Object recipeObj : manager.getRecipeList()) {
            IRecipe recipe = (IRecipe) recipeObj;
            if (recipe.matches(matrix, world)) {
                lastExtremeRecipe = recipe;
                return recipe.getCraftingResult(matrix);
            }
        }
        return null;
    }

    // Mirrors the shape test at the top of ExtremeCraftingManager.findMatchingRecipe (two lone
    // repairable items of the same type - note isRepairable(), not isDamageable(), unlike vanilla).
    @Unique
    private static boolean isRepairShaped(InventoryCrafting matrix) {
        int filled = 0;
        ItemStack first = null;
        ItemStack second = null;

        for (int i = 0; i < matrix.getSizeInventory(); i++) {
            ItemStack stack = matrix.getStackInSlot(i);
            if (stack != null) {
                if (filled == 0) first = stack;
                if (filled == 1) second = stack;
                filled++;
            }
        }

        return filled == 2 && first.getItem() == second.getItem()
                && first.stackSize == 1 && second.stackSize == 1
                && first.getItem().isRepairable();
    }
}
