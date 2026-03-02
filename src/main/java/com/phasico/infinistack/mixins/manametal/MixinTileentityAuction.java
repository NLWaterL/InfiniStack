package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.tileentity.TileentityAuction; // tileentity\TileentityAuction.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;


@Mixin(TileentityAuction.class)
@Pseudo
public abstract class MixinTileentityAuction {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}