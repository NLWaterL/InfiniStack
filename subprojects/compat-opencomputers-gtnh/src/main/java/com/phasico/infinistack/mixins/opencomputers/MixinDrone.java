package com.phasico.infinistack.mixins.opencomputers;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(targets = "li.cil.oc.common.entity.Drone$$anon$2", remap = false)
@Pseudo
public abstract class MixinDrone {

    @Overwrite
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
