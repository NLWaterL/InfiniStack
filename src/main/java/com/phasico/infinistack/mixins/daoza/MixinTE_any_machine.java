package com.phasico.infinistack.mixins.daoza;

import teplus.TE_anyinv_machine.TE_any_machine; // teplus\TE_anyinv_machine\TE_any_machine.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TE_any_machine.class)
public abstract class MixinTE_any_machine {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}