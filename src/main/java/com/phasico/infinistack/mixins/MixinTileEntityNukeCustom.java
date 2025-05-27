package com.phasico.infinistack.mixins;

import com.hbm.tileentity.bomb.TileEntityNukeCustom;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;



@Mixin(TileEntityNukeCustom.class)
public abstract class MixinTileEntityNukeCustom {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
