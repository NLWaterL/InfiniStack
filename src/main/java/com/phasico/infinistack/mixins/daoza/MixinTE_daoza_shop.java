package com.phasico.infinistack.mixins.daoza;

import teplus.TE_daoza_shop; // teplus\TE_daoza_shop.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TE_daoza_shop.class)
public abstract class MixinTE_daoza_shop {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}