package com.phasico.infinistack.mixins.daoza;

import container.Inv_bag; // container\Inv_bag.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(Inv_bag.class)
public abstract class MixinInv_bag {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}