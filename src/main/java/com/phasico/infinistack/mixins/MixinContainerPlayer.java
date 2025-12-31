package com.phasico.infinistack.mixins;

import com.phasico.infinistack.helper.Configurables;
import com.phasico.infinistack.helper.logic.InstantCraftingLogic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ContainerPlayer.class)
public abstract class MixinContainerPlayer {

    @Shadow
    public InventoryCrafting craftMatrix;

    @Shadow
    public IInventory craftResult;

    @Shadow @Final
    private EntityPlayer thePlayer;

    @Inject(method = "transferStackInSlot", at = @At("HEAD"), cancellable = true)
    private void fastCraftingLogic(EntityPlayer player, int slotIndex, CallbackInfoReturnable<ItemStack> cir) {

        if(!Configurables.enableFastCraft){
            return;
        }

        if (slotIndex == 0) {
            Slot slot = (Slot) ((Container)(Object)this).inventorySlots.get(0);
            ItemStack slotStack = slot.getStack();
            IRecipe recipe = findMatchingRecipe(craftMatrix);

            if (slotStack != null) {

                boolean success = InstantCraftingLogic.instantCraft(craftMatrix, (SlotCrafting)slot, recipe, player.inventory, player, 2);

                if (success) {

                    craftResult.setInventorySlotContents(0, null);

                    ((Container)(Object)this).onCraftMatrixChanged(craftMatrix);

                    ((Container)(Object)this).detectAndSendChanges();

                    cir.setReturnValue(null);

                } else {

                    //Edge case
                    cir.setReturnValue(null);

                }
            }
        }
    }

    private IRecipe findMatchingRecipe(InventoryCrafting craftMatrix) {
        World world = this.thePlayer.worldObj;
        for (Object recipeObj : CraftingManager.getInstance().getRecipeList()) {
            IRecipe recipe = (IRecipe) recipeObj;
            if (recipe.matches(craftMatrix, world)) {
                return recipe;
            }
        }
        return null;
    }
}