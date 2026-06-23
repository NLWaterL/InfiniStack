package com.phasico.infinistack.mixins.gregtech;

import gregtech.common.items.MetaGeneratedItem98;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Constant;
import com.phasico.infinistack.helper.Configurables;

@Mixin(MetaGeneratedItem98.class)
@Pseudo
public abstract class MixinMetaGeneratedItem98 {

    @ModifyConstant(
            method = "getItemStackLimit",
            constant = @Constant(intValue = 64),
            remap = false
    )
    public int replaceItemStackLimit(int original) {
        return Configurables.maxStackSize;
    }

}
