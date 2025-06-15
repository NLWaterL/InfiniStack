package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.tileentity.TileEntityOreMine; // tileentity\TileEntityOreMine.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityOreMine.class)
public abstract class MixinTileEntityOreMine {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}