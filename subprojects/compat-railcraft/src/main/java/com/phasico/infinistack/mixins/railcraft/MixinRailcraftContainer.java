package com.phasico.infinistack.mixins.railcraft;

import com.phasico.infinistack.helper.FixedCraftingContainer;
import com.phasico.infinistack.helper.InstantCraftToggle;
import com.phasico.infinistack.helper.logic.InstantCraftingLogic;
import mods.railcraft.common.gui.containers.RailcraftContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RailcraftContainer.class)
@Pseudo
public abstract class MixinRailcraftContainer implements FixedCraftingContainer, InstantCraftToggle {

    @Unique
    private boolean instantCraftEnabled = false;

    @Unique
    private int resultSlotId = -1;

    public boolean isInstantCraftEnabled() {
        return instantCraftEnabled;
    }

    public void setInstantCraftEnabled(boolean enabled) {
        instantCraftEnabled = enabled;
    }

    public Slot getResultSlot() {
        if (!this.getClass().getSimpleName().equals("ContainerCartWork")) {
            return null;
        }
        Container self = (Container) (Object) this;
        if (resultSlotId < 0) {
            for (int i = 0; i < self.inventorySlots.size(); i++) {
                if (self.inventorySlots.get(i) instanceof SlotCrafting) {
                    resultSlotId = i;
                    break;
                }
            }
        }
        return resultSlotId < 0 ? null : self.getSlot(resultSlotId);
    }

    public int getResultSlotSize() {
        return 26;
    }

    @Inject(method = "func_82846_b", at = @At("HEAD"), cancellable = true, remap = false)
    private void fastCraftingLogic(EntityPlayer player, int slotIndex, CallbackInfoReturnable<ItemStack> cir) {

        if (!instantCraftEnabled) {
            return;
        }

        if(slotIndex < 0 || slotIndex >= ((Container)(Object)this).inventorySlots.size()){
            return;
        }

        Slot slot = (Slot) ((Container) (Object) this).inventorySlots.get(slotIndex);

        if (this.getClass().getSimpleName().equals("ContainerCartWork")) {

            if (slot instanceof SlotCrafting) {

                InventoryCrafting craftMatrix = ((AccessorContainerCartWork)this).getCraftMatrix();

                IInventory craftResult = ((AccessorContainerCartWork)this).getCraftResult();

                ItemStack slotStack = slot.getStack();
                IRecipe recipe = findMatchingRecipe(craftMatrix, player.worldObj);

                if (slotStack != null) {

                    boolean success = InstantCraftingLogic.instantCraft(craftMatrix, (SlotCrafting) slot, recipe, player, 3);

                    if (success) {

                        craftResult.setInventorySlotContents(0, null);

                        ((Container) (Object) this).onCraftMatrixChanged(craftMatrix);

                        ((Container) (Object) this).detectAndSendChanges();

                        cir.setReturnValue(null);

                    }
                }
            }
        }
    }

    @Unique
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
