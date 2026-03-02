package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.Lapuda.EntityBossHydraItemCore; // Lapuda\EntityBossHydraItemCore.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;


@Mixin(EntityBossHydraItemCore.class)
@Pseudo
public abstract class MixinEntityBossHydraItemCore {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}