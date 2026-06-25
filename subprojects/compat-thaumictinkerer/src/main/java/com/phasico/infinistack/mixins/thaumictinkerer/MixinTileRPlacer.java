package com.phasico.infinistack.mixins.thaumictinkerer;

import thaumic.tinkerer.common.block.tile.TileRPlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileRPlacer.class)
@Pseudo
public abstract class MixinTileRPlacer {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
