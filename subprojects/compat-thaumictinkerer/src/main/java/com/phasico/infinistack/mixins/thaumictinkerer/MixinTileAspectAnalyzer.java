package com.phasico.infinistack.mixins.thaumictinkerer;

import thaumic.tinkerer.common.block.tile.TileAspectAnalyzer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileAspectAnalyzer.class)
@Pseudo
public abstract class MixinTileAspectAnalyzer {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
