package com.phasico.infinistack.mixins.bibliocraft;

import com.phasico.infinistack.helper.FixedCraftingContainer;
import com.phasico.infinistack.helper.InstantCraftToggle;
import com.phasico.infinistack.helper.logic.InstantCraftingLogic;
import jds.bibliocraft.blocks.ContainerFancyWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ContainerFancyWorkbench.class)
@Pseudo
public abstract class MixinContainerFancyWorkbench implements FixedCraftingContainer, InstantCraftToggle {

    @Shadow(remap = false)
    public InventoryCrafting playerCraftMatrix;

    @Shadow(remap = false)
    public IInventory craftResult;

    @Shadow(remap = false)
    private World world;

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

        Slot slot = (Slot) ((Container)(Object)this).inventorySlots.get(slotIndex);

        if (slot instanceof SlotCrafting) {
            ItemStack slotStack = slot.getStack();
            IRecipe recipe = findMatchingRecipe(playerCraftMatrix, world);

            if (slotStack != null) {

                boolean success = InstantCraftingLogic.instantCraft(playerCraftMatrix, (SlotCrafting)slot, recipe, player, 3);

                if (success) {

                    craftResult.setInventorySlotContents(0, null);

                    ((Container)(Object)this).onCraftMatrixChanged(playerCraftMatrix);

                    ((Container)(Object)this).detectAndSendChanges();

                    cir.setReturnValue(null);

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
