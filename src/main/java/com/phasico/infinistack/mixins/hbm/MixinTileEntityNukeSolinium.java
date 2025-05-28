package com.phasico.infinistack.mixins.hbm;

import com.hbm.tileentity.bomb.TileEntityNukeSolinium;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;



@Mixin(TileEntityNukeSolinium.class)
public abstract class MixinTileEntityNukeSolinium {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
