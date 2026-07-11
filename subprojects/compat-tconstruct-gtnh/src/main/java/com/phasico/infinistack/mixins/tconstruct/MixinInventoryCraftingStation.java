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

    @Redirect(
        method = {"func_70298_a", "func_70299_a"},
        at = @At(value = "INVOKE",
                 target = "Lnet/minecraft/inventory/Container;func_75130_a(Lnet/minecraft/inventory/IInventory;)V"),
        remap = false
    )
    private void suppressibleCraftMatrixChanged(Container handler, IInventory matrix) {
        if (!((SuppressibleCraftMatrix) (Object) this).isMatrixEventsSuppressed()) {
            handler.onCraftMatrixChanged(matrix);
        }
    }
}
