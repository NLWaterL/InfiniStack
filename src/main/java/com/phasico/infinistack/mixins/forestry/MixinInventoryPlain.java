package com.phasico.infinistack.mixins.forestry;

import forestry.core.inventory.InventoryPlain;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Constant;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryPlain.class)
public abstract class MixinInventoryPlain {

    @ModifyConstant(
            method = "<init>(ILjava/lang/String;)V",
            constant = @Constant(intValue = 64),
            remap = false
    )
    private static int modifyDefaultStackLimit(int original) {
        return Configurables.maxStackSize;
    }
}