package com.phasico.infinistack.mixins.projecte;

import moze_intel.projecte.gameObjs.tiles.RMFurnaceTile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(RMFurnaceTile.class)
public abstract class MixinRMFurnaceTile {

    @Overwrite(remap = false)
    public int func_70297_j_()
    {
        return Configurables.maxStackSize;
    }

}
