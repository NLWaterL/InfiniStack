package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.totem.TileEntityTotem; // totem\TileEntityTotem.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;


@Mixin(TileEntityTotem.class)
@Pseudo
public abstract class MixinTileEntityTotem {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}