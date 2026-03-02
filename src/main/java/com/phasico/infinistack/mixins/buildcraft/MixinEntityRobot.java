package com.phasico.infinistack.mixins.buildcraft;

import buildcraft.robotics.EntityRobot;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;



@Mixin(EntityRobot.class)
@Pseudo
public abstract class MixinEntityRobot {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
