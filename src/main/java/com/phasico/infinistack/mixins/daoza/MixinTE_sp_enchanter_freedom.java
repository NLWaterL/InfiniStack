package com.phasico.infinistack.mixins.daoza;

import teplus.TE_sp_enchanter_freedom; // teplus\TE_sp_enchanter_freedom.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TE_sp_enchanter_freedom.class)
public abstract class MixinTE_sp_enchanter_freedom {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}