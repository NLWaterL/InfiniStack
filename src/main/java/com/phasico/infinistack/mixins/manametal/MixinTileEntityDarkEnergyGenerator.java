package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.dark_magic.TileEntityDarkEnergyGenerator; // dark_magic\TileEntityDarkEnergyGenerator.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;


@Mixin(TileEntityDarkEnergyGenerator.class)
@Pseudo
public abstract class MixinTileEntityDarkEnergyGenerator {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}