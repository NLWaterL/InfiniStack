package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.tileentity.TileEntityBedrockCrusher; // tileentity\TileEntityBedrockCrusher.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;


@Mixin(TileEntityBedrockCrusher.class)
@Pseudo
public abstract class MixinTileEntityBedrockCrusher {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}