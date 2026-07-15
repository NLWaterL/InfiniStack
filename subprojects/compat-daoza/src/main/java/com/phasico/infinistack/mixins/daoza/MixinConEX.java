package com.phasico.infinistack.mixins.daoza;

import com.daozcraft.container.ConEX;
import com.phasico.infinistack.helper.FixedCraftingContainer;
import com.phasico.infinistack.helper.InstantCraftToggle;
import com.phasico.infinistack.helper.logic.InstantCraftingLogic;
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

@Mixin(ConEX.class)
@Pseudo
public abstract class MixinConEX implements FixedCraftingContainer, InstantCraftToggle {

    @Shadow(remap = false)
    public InventoryCrafting craftMatrix;

    @Shadow(remap = false)
    public IInventory craftResult;

    @Shadow(remap = false)
    public int slot_craft_result;

    @Shadow(remap = false)
    public abstract void save3X3AndMarkInvEX();

    @Unique
    private boolean instantCraftEnabled = false;

    public boolean isInstantCraftEnabled() {
        return instantCraftEnabled;
    }

    public void setInstantCraftEnabled(boolean enabled) {
        instantCraftEnabled = enabled;
    }

    // ConEX already stores its result slot id in slot_craft_result, so no scan is needed.
    public Slot getResultSlot() {
        Container self = (Container) (Object) this;
        if (slot_craft_result < 0 || slot_craft_result >= self.inventorySlots.size()) {
            return null;
        }
        Slot slot = self.getSlot(slot_craft_result);
        return slot instanceof SlotCrafting ? slot : null;
    }

    public int getResultSlotSize() {
        return 16;
    }

    public int getButtonGap() {
        return 12;
    }

    public int getButtonHorizontalShift() {
        return -1;
    }

    @Inject(method = "func_82846_b", at = @At("HEAD"), cancellable = true, remap = false)
    private void fastCraftingLogic(EntityPlayer player, int slotIndex, CallbackInfoReturnable<ItemStack> cir) {

        if (!instantCraftEnabled) {
            return;
        }

        if(!(slotIndex == slot_craft_result)){
            return;
        }

        Slot slot = (Slot)((Container)(Object)this).inventorySlots.get(slotIndex);
        if (slot instanceof SlotCrafting) {
            ItemStack slotStack = slot.getStack();
            IRecipe recipe = findMatchingRecipe(craftMatrix, player.worldObj);

            if (slotStack != null) {

                boolean success = InstantCraftingLogic.instantCraft(craftMatrix, (SlotCrafting)slot, recipe, player, 3);

                if (success) {

                    craftResult.setInventorySlotContents(0, null);

                    ((Container)(Object)this).onCraftMatrixChanged(craftMatrix);

                    save3X3AndMarkInvEX();

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
