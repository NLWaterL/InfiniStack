package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.tileentity.TileEntityManaCraftTable; // tileentity\TileEntityManaCraftTable.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityManaCraftTable.class)
public abstract class MixinTileEntityManaCraftTable {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}