package com.phasico.infinistack.mixins.avaritia;

import com.phasico.infinistack.helper.SuppressibleCraftMatrix;
import fox.spiteful.avaritia.tile.inventory.InventoryDireCrafting;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

// InventoryDireCrafting overrides decrStackSize/setInventorySlotContents with tile-backed storage
// and calls container.func_75130_a directly, bypassing the vanilla InventoryCrafting code that
// MixinInventoryCrafting patches - so its fires get the same suppression check here. The flag
// itself lives in the InventoryCrafting superclass (set by MixinSlotCrafting around each craft);
// super's eventHandler is wired too, so the duck's getEventHandler needs no override.
@Mixin(InventoryDireCrafting.class)
@Pseudo
public abstract class MixinInventoryDireCrafting {

    @Redirect(
        method = {"func_70298_a", "func_70299_a"},
        at = @At(value = "INVOKE",
                 target = "Lnet/minecraft/inventory/Container;func_75130_a(Lnet/minecraft/inventory/IInventory;)V"),
        remap = false
    )
    private void suppressableCraftMatrixChanged(Container handler, IInventory matrix) {
        if (!((SuppressibleCraftMatrix) (Object) this).isMatrixEventsSuppressed()) {
            handler.onCraftMatrixChanged(matrix);
        }
    }
}
