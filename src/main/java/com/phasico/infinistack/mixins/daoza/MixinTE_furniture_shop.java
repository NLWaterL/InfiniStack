package com.phasico.infinistack.mixins.daoza;

import teplus.TE_furniture_shop; // teplus\TE_furniture_shop.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TE_furniture_shop.class)
public abstract class MixinTE_furniture_shop {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}