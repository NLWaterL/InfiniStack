package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.tileentity.TileEntityEXPExtractor; // tileentity\TileEntityEXPExtractor.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityEXPExtractor.class)
public abstract class MixinTileEntityEXPExtractor {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}