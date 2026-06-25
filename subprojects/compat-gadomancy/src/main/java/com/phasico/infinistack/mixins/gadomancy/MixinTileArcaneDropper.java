package com.phasico.infinistack.mixins.gadomancy;

import makeo.gadomancy.common.blocks.tiles.TileArcaneDropper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileArcaneDropper.class)
@Pseudo
public abstract class MixinTileArcaneDropper {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
