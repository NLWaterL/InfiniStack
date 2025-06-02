package com.phasico.infinistack.mixins.projecte;

import moze_intel.projecte.gameObjs.tiles.CondenserTile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(CondenserTile.class)
public abstract class MixinCondenserTile {

    @Overwrite(remap = false)
    public int func_70297_j_()
    {
        return Configurables.maxStackSize;
    }

}
