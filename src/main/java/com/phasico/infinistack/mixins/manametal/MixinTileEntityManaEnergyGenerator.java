package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.tileentity.TileEntityManaEnergyGenerator; // tileentity\TileEntityManaEnergyGenerator.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;


@Mixin(TileEntityManaEnergyGenerator.class)
@Pseudo
public abstract class MixinTileEntityManaEnergyGenerator {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}