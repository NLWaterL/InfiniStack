package com.phasico.infinistack.mixins.projecte;

import moze_intel.projecte.gameObjs.tiles.AlchChestTile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(AlchChestTile.class)
public abstract class MixinAlchChestTile {

    @Overwrite(remap = false)
    public int func_70297_j_()
    {
        return Configurables.maxStackSize;
    }

}
