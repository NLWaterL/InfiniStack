package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.tileentity.TileEntityMetalFurnace; // tileentity\TileEntityMetalFurnace.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityMetalFurnace.class)
public abstract class MixinTileEntityMetalFurnace {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}