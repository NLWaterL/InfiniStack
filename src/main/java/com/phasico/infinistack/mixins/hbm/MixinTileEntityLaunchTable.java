package com.phasico.infinistack.mixins.hbm;

import com.hbm.tileentity.bomb.TileEntityLaunchTable; // hbm\tileentity\bomb\TileEntityLaunchTable.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityLaunchTable.class)
@Pseudo
public abstract class MixinTileEntityLaunchTable {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}