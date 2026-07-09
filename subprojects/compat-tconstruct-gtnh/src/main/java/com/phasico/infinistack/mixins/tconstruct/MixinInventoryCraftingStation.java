package com.phasico.infinistack.mixins.tconstruct;

import com.phasico.infinistack.helper.Configurables;
import com.phasico.infinistack.helper.SuppressibleCraftMatrix;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tconstruct.tools.inventory.InventoryCraftingStation;

@Mixin(InventoryCraftingStation.class)
@Pseudo
public abstract class MixinInventoryCraftingStation {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

    // InventoryCraftingStation overrides decrStackSize/setInventorySlotContents with tile-backed
    // storage and calls its own eventHandler.func_75130_a directly, bypassing the vanilla
    // InventoryCrafting code that MixinInventoryCrafting patches - so its fires get the same
    // suppression check here. The flag lives in the InventoryCrafting superclass (set by
    // MixinSlotCrafting around each craft); super's eventHandler is wired too, so the duck's
    // getEventHandler needs no override.
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
