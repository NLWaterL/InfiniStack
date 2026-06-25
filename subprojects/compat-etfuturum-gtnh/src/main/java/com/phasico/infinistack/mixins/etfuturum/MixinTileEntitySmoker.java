package com.phasico.infinistack.mixins.etfuturum;

import ganymedes01.etfuturum.tileentities.TileEntitySmoker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntitySmoker.class)
@Pseudo
public abstract class MixinTileEntitySmoker {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
