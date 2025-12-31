package com.phasico.infinistack.mixins.forestry;

import com.phasico.infinistack.helper.Configurables;
import forestry.core.inventory.InventoryAdapterTile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Constant;

@Mixin(InventoryAdapterTile.class)
public abstract class MixinInventoryAdapterTile {

    @ModifyConstant(
            method = "<init>(Lnet/minecraft/tileentity/TileEntity;ILjava/lang/String;)V",
            constant = @Constant(intValue = 64),
            remap = false
    )
    private static int modifyDefaultStackLimit(int original) {
        return Configurables.maxStackSize;
    }

}
