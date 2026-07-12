package com.phasico.infinistack.mixins;

import com.phasico.infinistack.helper.FixedCraftingContainer;
import com.phasico.infinistack.helper.InstantCraftToggle;
import com.phasico.infinistack.helper.logic.InstantCraftingLogic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ContainerWorkbench.class)
public abstract class MixinContainerWorkbench implements FixedCraftingContainer, InstantCraftToggle {

    @Shadow
    public InventoryCrafting craftMatrix;

    @Shadow
    public IInventory craftResult;

    @Shadow
    private World worldObj;

    @Unique
    private boolean instantCraftEnabled = false;

    public boolean isInstantCraftEnabled() {
        return instantCraftEnabled;
    }

    public void setInstantCraftEnabled(boolean enabled) {
        instantCraftEnabled = enabled;
    }

    @Inject(method = "transferStackInSlot", at = @At("HEAD"), cancellable = true)
    private void fastCraftingLogic(EntityPlayer player, int slotIndex, CallbackInfoReturnable<ItemStack> cir) {

        if (!instantCraftEnabled) {
            return;
        }

        if (slotIndex < 0 || slotIndex >= ((Container) (Object) this).inventorySlots.size()) {
            return;
        }

        Slot slot = (Slot) ((Container) (Object) this).inventorySlots.get(slotIndex);

        if (slot instanceof SlotCrafting) {
            ItemStack slotStack = slot.getStack();
            IRecipe recipe = findMatchingRecipe(craftMatrix, worldObj);

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

    @Unique
    private IRecipe findMatchingRecipe(InventoryCrafting matrix, World world) {
        for (Object recipeObj : CraftingManager.getInstance().getRecipeList()) {
            IRecipe recipe = (IRecipe) recipeObj;
            if (recipe.matches(matrix, world)) {
                return recipe;
            }
        }
        return null;
    }
}
