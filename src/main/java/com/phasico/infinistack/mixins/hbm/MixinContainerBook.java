package com.phasico.infinistack.mixins.hbm;

import com.hbm.inventory.container.ContainerBook;
import com.hbm.inventory.recipes.MagicRecipes;
import com.phasico.infinistack.helper.Configurables;
import com.phasico.infinistack.helper.logic.InstantCraftingLogic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ContainerBook.class)
public abstract class MixinContainerBook {

    @Shadow(remap = false)
    public InventoryCrafting craftMatrix;

    @Inject(method = "func_82846_b", at = @At("HEAD"), remap = false, cancellable = true)
    private void injectTransferStackInSlot(EntityPlayer player, int slotIndex, CallbackInfoReturnable<ItemStack> cir) {

        if(!Configurables.enableFastCraft){
            return;
        }

        if (slotIndex == 0) {

            ItemStack recipeResult = MagicRecipes.getRecipe(craftMatrix);
            if(recipeResult == null){
                cir.setReturnValue(null);
                return;
            }
            ItemStack result = recipeResult.copy();

            int maxCraft = InstantCraftingLogic.calculateMaxCraft(craftMatrix,2);

            long inventorySpace = InstantCraftingLogic.calculateMaxFit(player.inventory, result);

            maxCraft = (int)Math.min(maxCraft, inventorySpace);
            if(maxCraft <= 0){
                cir.setReturnValue(null);
                return;
            }

            result.stackSize = maxCraft;

            InstantCraftingLogic.consumeIngredients(craftMatrix, maxCraft, player.inventory, player, 2);

            InstantCraftingLogic.returnResult(player.inventory, result, player);

            ((ContainerBook)(Object)this).onCraftMatrixChanged(craftMatrix);

            cir.setReturnValue(null);

        }
    }

}