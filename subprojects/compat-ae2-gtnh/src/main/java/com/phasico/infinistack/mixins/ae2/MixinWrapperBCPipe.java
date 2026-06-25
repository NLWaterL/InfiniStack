package com.phasico.infinistack.mixins.ae2;

import appeng.util.inv.WrapperBCPipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(WrapperBCPipe.class)
@Pseudo
public abstract class MixinWrapperBCPipe {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
