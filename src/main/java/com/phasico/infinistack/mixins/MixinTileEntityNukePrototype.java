package com.phasico.infinistack.mixins;

import com.hbm.tileentity.bomb.TileEntityNukePrototype;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;



@Mixin(TileEntityNukePrototype.class)
public abstract class MixinTileEntityNukePrototype {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
