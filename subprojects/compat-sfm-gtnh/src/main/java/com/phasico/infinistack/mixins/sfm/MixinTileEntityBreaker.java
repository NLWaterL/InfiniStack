package com.phasico.infinistack.mixins.sfm;

import vswe.stevesfactory.blocks.TileEntityBreaker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityBreaker.class)
@Pseudo
public abstract class MixinTileEntityBreaker {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
