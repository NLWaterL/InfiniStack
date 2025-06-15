package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.produce.casting.TileEntityCastingTable; // produce\casting\TileEntityCastingTable.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityCastingTable.class)
public abstract class MixinTileEntityCastingTable {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}