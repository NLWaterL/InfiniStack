package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.tileentity.TileEntityTimeFurnace; // tileentity\TileEntityTimeFurnace.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityTimeFurnace.class)
public abstract class MixinTileEntityTimeFurnace {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}