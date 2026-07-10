package com.phasico.infinistack.mixins.remoteio;


import com.google.common.collect.Lists;
import com.phasico.infinistack.helper.FixedCraftingContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import remoteio.common.inventory.InventoryTileCrafting;
import remoteio.common.inventory.container.ContainerIntelligentWorkbench;
import remoteio.common.tile.TileIntelligentWorkbench;

import java.util.List;

import static remoteio.common.inventory.container.ContainerIntelligentWorkbench.getAllCraftingResults;

@Mixin(ContainerIntelligentWorkbench.class)
@Pseudo
public abstract class MixinContainerIntelligentWorkbench implements FixedCraftingContainer {

    @Shadow(remap = false)
    @Final
    public TileIntelligentWorkbench tileIntelligentWorkbench;

    @Shadow(remap = false)
    public IInventory craftResult;

    @Shadow(remap = false)
    private int recipeIndex;

    @Shadow(remap = false)
    private List<ItemStack> results;

    @Shadow(remap = false)
    public abstract void func_75142_b();

    @Unique
    private List<IRecipe> lastRecipes = null;

    @Overwrite(remap = false)
    public void func_75130_a(IInventory inventory) {

        InventoryTileCrafting craftMatrix = tileIntelligentWorkbench.craftMatrix;
        World world = tileIntelligentWorkbench.getWorldObj();

        boolean cacheHit = lastRecipes != null && !lastRecipes.isEmpty() && !lastRecipes.contains(null);
        if (cacheHit) {
            for (IRecipe recipe : lastRecipes) {
                if (!recipe.matches(craftMatrix, world)) {
                    cacheHit = false;
                    break;
                }
            }
        }

        if (!cacheHit) {
            lastRecipes = findAllMatchingRecipe(craftMatrix, world);
            recipeIndex = 0;
        }

        if (lastRecipes.contains(null)) {
            results = getAllCraftingResults(craftMatrix, world);
        } else {
            results = Lists.newArrayList();
            for (IRecipe recipe : lastRecipes) {
                results.add(recipe.getCraftingResult(craftMatrix));
            }
        }

        this.craftResult.setInventorySlotContents(0, !results.isEmpty() ? results.get(recipeIndex).copy() : null);

        if(!cacheHit) func_75142_b();
    }

    @Redirect(
            method = "func_75140_a",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;get(I)Ljava/lang/Object;"
            ), remap = false
    )
    private Object returnCopy(List self, int index){
        return this.results.get(index).copy();
    }

    @Unique
    private static List<IRecipe> findAllMatchingRecipe(InventoryTileCrafting craftMatrix, World world) {
        List<IRecipe> list = Lists.newArrayList();

        int i = 0;
        ItemStack itemstack = null;
        ItemStack itemstack1 = null;
        int j;

        for (j = 0; j < craftMatrix.getSizeInventory(); ++j) {
            ItemStack itemstack2 = craftMatrix.getStackInSlot(j);

            if (itemstack2 != null) {
                if (i == 0) {
                    itemstack = itemstack2;
                }

                if (i == 1) {
                    itemstack1 = itemstack2;
                }

                ++i;
            }
        }

        if (i == 2 && itemstack.getItem() == itemstack1.getItem() && itemstack.stackSize == 1 && itemstack1.stackSize == 1 && itemstack.getItem().isRepairable()) {
            //Repair recipe = null
            list.add(null);
        } else {
            for (Object obj : CraftingManager.getInstance().getRecipeList()) {
                IRecipe recipe = (IRecipe) obj;
                if (recipe.matches(craftMatrix, world)) {
                    list.add(recipe);
                }
            }
        }

        return list;
    }

}
