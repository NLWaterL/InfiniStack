package com.phasico.infinistack.mixins.daoza;

import container.Inv_rcp_paper; // container\Inv_rcp_paper.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(Inv_rcp_paper.class)
public abstract class MixinInv_rcp_paper {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}