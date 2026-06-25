package com.phasico.infinistack.mixins.sfm;

import vswe.stevesfactory.blocks.TileEntityCreative;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityCreative.class)
@Pseudo
public abstract class MixinTileEntityCreative {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
