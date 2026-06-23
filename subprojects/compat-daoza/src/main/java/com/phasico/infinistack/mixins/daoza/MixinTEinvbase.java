package com.phasico.infinistack.mixins.daoza;

import com.daozcraft.teplus.TEinvbase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Pseudo;

@Mixin(TEinvbase.class)
@Pseudo
public abstract class MixinTEinvbase {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}