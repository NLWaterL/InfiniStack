package com.phasico.infinistack.mixins.remoteio;

import com.phasico.infinistack.helper.Configurables;
import com.phasico.infinistack.helper.SuppressibleCraftMatrix;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import remoteio.common.inventory.InventoryTileCrafting;

@Mixin(InventoryTileCrafting.class)
@Pseudo
public abstract class MixinInventoryTileCrafting implements SuppressibleCraftMatrix {

    @Shadow(remap = false)
    private Container eventHandler;

    @Override
    public Container getEventHandler() {
        return eventHandler;
    }

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
        if (!isMatrixEventsSuppressed()) {
            handler.onCraftMatrixChanged(matrix);
        }
    }
}
