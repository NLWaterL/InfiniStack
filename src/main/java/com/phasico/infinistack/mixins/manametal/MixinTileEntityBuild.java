package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.blueprint.TileEntityBuild; // blueprint\TileEntityBuild.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityBuild.class)
public abstract class MixinTileEntityBuild {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}