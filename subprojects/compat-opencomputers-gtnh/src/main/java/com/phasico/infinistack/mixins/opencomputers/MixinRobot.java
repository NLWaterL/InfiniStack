package com.phasico.infinistack.mixins.opencomputers;

import li.cil.oc.common.tileentity.Robot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(Robot.class)
@Pseudo
public abstract class MixinRobot {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
