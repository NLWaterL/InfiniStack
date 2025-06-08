package com.phasico.infinistack.mixins.forestry;

import com.phasico.infinistack.helper.Configurables;
import forestry.core.inventory.InventoryAdapter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Constant;

@Mixin(InventoryAdapter.class)
public abstract class MixinInventoryAdapter {

    @ModifyConstant(
            method = "<init>(ILjava/lang/String;)V",
            constant = @Constant(intValue = 64),
            remap = false
    )
    private static int modifyDefaultStackLimit(int original) {
        return Configurables.maxStackSize;
    }
}
