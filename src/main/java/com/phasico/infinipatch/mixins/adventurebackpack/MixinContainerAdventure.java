package com.phasico.infinipatch.mixins.adventurebackpack;

import com.darkona.adventurebackpack.inventory.InventoryCraftingBackpack;
import com.phasico.infinistack.helper.Configurables;
import com.phasico.infinistack.helper.logic.InstantCraftingLogic;
import com.darkona.adventurebackpack.inventory.ContainerAdventure;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ContainerAdventure.class)
public abstract class MixinContainerAdventure {

    @Inject(
            method = "func_82846_b",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private void injectTransferStackInSlot(EntityPlayer player, int slotId, CallbackInfoReturnable<ItemStack> cir) {

        if(!Configurables.enableFastCraft){
            return;
        }

        if (this.getClass().getSimpleName().equals("ContainerBackpack")) {
            //99 is the result slot.
            if (slotId == 99) {

                InventoryCraftingBackpack craftMatrix = ((AccessorContainerBackpack)this).getCraftMatrix();

                IInventory craftResult = ((AccessorContainerBackpack)this).getCraftResult();

                Slot slot = (Slot) ((Container) (Object) this).inventorySlots.get(99);
                ItemStack slotStack = slot.getStack();
                IRecipe recipe = findMatchingRecipe(craftMatrix, player.worldObj);

                if (slotStack != null) {

                    ((AccessorContainerBackpack)this).invokeSyncCraftMatrixWithInventory(true);

                    boolean success = InstantCraftingLogic.instantCraft(craftMatrix, (SlotCrafting) slot, recipe, player.inventory, player, 3);

                    if (success) {

                        craftResult.setInventorySlotContents(0, null);

                        ((AccessorContainerBackpack)this).invokeSyncCraftMatrixWithInventory(false);

                        cir.setReturnValue(null);

                    } else {

                        //Edge case
                        cir.setReturnValue(null);

                    }
                }
            }
        }
    }

        private IRecipe findMatchingRecipe (InventoryCrafting craftMatrix, World world){
            for (Object recipeObj : CraftingManager.getInstance().getRecipeList()) {
                IRecipe recipe = (IRecipe) recipeObj;
                if (recipe.matches(craftMatrix, world)) {
                    return recipe;
                }
            }
            return null;
        }
}
