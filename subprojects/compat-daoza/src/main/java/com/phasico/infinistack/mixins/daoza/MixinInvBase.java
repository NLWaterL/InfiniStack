package com.phasico.infinistack.mixins.daoza;

import com.daozcraft.container.InvBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Pseudo;

@Mixin(InvBase.class)
@Pseudo
public abstract class MixinInvBase {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}