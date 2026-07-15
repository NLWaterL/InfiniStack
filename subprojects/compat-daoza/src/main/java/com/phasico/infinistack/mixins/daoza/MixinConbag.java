package com.phasico.infinistack.mixins.daoza;

import com.daozcraft.container.Conbag;
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

import static com.daozcraft.container.Conbag.TOTALSLOT_CRAFT;

@Mixin(Conbag.class)
@Pseudo
public abstract class MixinConbag implements FixedCraftingContainer, InstantCraftToggle {

    @Shadow(remap = false)
    public InventoryCrafting craft3x3;

    @Shadow(remap = false)
    public IInventory craftResult;

    @Shadow(remap = false)
    public static int totalslot;

    @Shadow(remap = false)
    protected abstract void markDirty();

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
        if (!(totalslot == TOTALSLOT_CRAFT)) {
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
        return 16;
    }

    @Inject(method = "func_82846_b", at = @At("HEAD"), cancellable = true, remap = false)
    private void fastCraftingLogic(EntityPlayer player, int slotIndex, CallbackInfoReturnable<ItemStack> cir) {

        if (!instantCraftEnabled) {
            return;
        }

        if(slotIndex < 0 || slotIndex >= ((Container)(Object)this).inventorySlots.size()){
            return;
        }

        if(!(totalslot == TOTALSLOT_CRAFT)){
            return;
        }

        Slot slot = (Slot)((Container)(Object)this).inventorySlots.get(slotIndex);

        if (slot instanceof SlotCrafting) {
            ItemStack slotStack = slot.getStack();
            IRecipe recipe = findMatchingRecipe(craft3x3, player.worldObj);

            if (slotStack != null) {

                boolean success = InstantCraftingLogic.instantCraft(craft3x3, (SlotCrafting)slot, recipe, player, 3);

                if (success) {

                    craftResult.setInventorySlotContents(0, null);

                    ((Container)(Object)this).onCraftMatrixChanged(craft3x3);

                    markDirty();

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
