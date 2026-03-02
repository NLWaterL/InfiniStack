package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.tileentity.TileEntityCrusherMetal; // tileentity\TileEntityCrusherMetal.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;


@Mixin(TileEntityCrusherMetal.class)
@Pseudo
public abstract class MixinTileEntityCrusherMetal {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}