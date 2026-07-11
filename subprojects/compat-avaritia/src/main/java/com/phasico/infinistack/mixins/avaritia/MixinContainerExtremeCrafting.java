package com.phasico.infinistack.mixins.avaritia;


import com.phasico.infinistack.helper.logic.InstantCraftingLogic;
import fox.spiteful.avaritia.crafting.ExtremeCraftingManager;
import fox.spiteful.avaritia.gui.ContainerExtremeCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(ContainerExtremeCrafting.class)
@Pseudo
public abstract class MixinContainerExtremeCrafting {

    @Shadow(remap = false)
    public InventoryCrafting craftMatrix;

    @Shadow(remap = false)
    public IInventory craftResult;

    @Shadow(remap = false)
    private World worldObj;

    @Inject(method = "func_82846_b", at = @At("HEAD"), cancellable = true, remap = false)
    private void fastCraftingLogic(EntityPlayer player, int slotIndex, CallbackInfoReturnable<ItemStack> cir) {

        if (slotIndex < 0 || slotIndex >= ((Container) (Object) this).inventorySlots.size()) {
            return;
        }

        Slot slot = (Slot) ((Container) (Object) this).inventorySlots.get(slotIndex);

        if (slot instanceof SlotCrafting) {
            ItemStack slotStack = slot.getStack();
            IRecipe recipe = findMatchingExtremeRecipe(craftMatrix, worldObj);

            if (slotStack != null) {

                boolean success = InstantCraftingLogic.instantCraft(craftMatrix, (SlotCrafting) slot, recipe, player, 9);

                if (success) {

                    craftResult.setInventorySlotContents(0, null);

                    ((Container) (Object) this).onCraftMatrixChanged(craftMatrix);

                    ((Container) (Object) this).detectAndSendChanges();

                    cir.setReturnValue(null);

                }
            }
        }
    }

    private IRecipe findMatchingExtremeRecipe(InventoryCrafting matrix, World world) {
        for (Object recipeObj : ExtremeCraftingManager.getInstance().getRecipeList()) {
            IRecipe recipe = (IRecipe) recipeObj;
            if (recipe.matches(matrix, world)) {
                return recipe;
            }
        }
        return null;
    }

}
