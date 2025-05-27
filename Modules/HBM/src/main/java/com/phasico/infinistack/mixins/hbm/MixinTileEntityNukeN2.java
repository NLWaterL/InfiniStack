package com.phasico.infinistack.mixins.hbm;

import com.hbm.tileentity.bomb.TileEntityNukeN2;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

@Pseudo
@Mixin(TileEntityNukeN2.class)
public abstract class MixinTileEntityNukeN2 {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
