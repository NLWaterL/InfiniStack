package com.phasico.infinistack.mixins.adventurebackpack;

import com.darkona.adventurebackpack.inventory.ContainerBackpack;
import com.phasico.infinistack.helper.Configurables;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import com.phasico.infinistack.helper.logic.InstantCraftingLogic;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ContainerBackpack.class)
public abstract class MixinContainerBackpack {

    @Shadow(remap = false)
    public InventoryCrafting craftMatrix;

    @Shadow(remap = false)
    public IInventory craftResult;

    @Inject(
            method = "func_82846_b",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private void injectTransferStackInSlot(EntityPlayer player, int slotId, CallbackInfoReturnable<ItemStack> cir){

        if(!Configurables.enableFastCraft){
            return;
        }

        //90 is the result slot.
        if (slotId == 90){
            Slot slot = (Slot) ((Container)(Object)this).inventorySlots.get(90);
            ItemStack slotStack = slot.getStack();
            IRecipe recipe = findMatchingRecipe(craftMatrix, player.worldObj);

            if (slotStack != null) {

                boolean success = InstantCraftingLogic.instantCraft(craftMatrix, (SlotCrafting)slot, recipe, player.inventory, player, 3);

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

    private IRecipe findMatchingRecipe(InventoryCrafting craftMatrix, World world) {
        for (Object recipeObj : CraftingManager.getInstance().getRecipeList()) {
            IRecipe recipe = (IRecipe) recipeObj;
            if (recipe.matches(craftMatrix, world)) {
                return recipe;
            }
        }
        return null;
    }
}
