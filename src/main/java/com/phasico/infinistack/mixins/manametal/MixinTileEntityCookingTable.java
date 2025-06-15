package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.produce.cuisine.TileEntityCookingTable; // produce\cuisine\TileEntityCookingTable.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityCookingTable.class)
public abstract class MixinTileEntityCookingTable {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}