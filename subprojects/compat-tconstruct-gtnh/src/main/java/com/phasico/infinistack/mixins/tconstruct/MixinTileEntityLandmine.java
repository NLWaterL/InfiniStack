package com.phasico.infinistack.mixins.tconstruct;

import tconstruct.mechworks.logic.TileEntityLandmine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityLandmine.class)
@Pseudo
public abstract class MixinTileEntityLandmine {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
