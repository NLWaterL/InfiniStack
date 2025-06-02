package com.phasico.infinistack.mixins.projecte;

import moze_intel.projecte.gameObjs.tiles.CollectorMK1Tile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(CollectorMK1Tile.class)
public abstract class MixinCollectorMK1Tile {

    @Overwrite(remap = false)
    public int func_70297_j_()
    {
        return Configurables.maxStackSize;
    }

}
