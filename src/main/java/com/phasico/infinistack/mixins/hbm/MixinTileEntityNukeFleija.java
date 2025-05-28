package com.phasico.infinistack.mixins.hbm;

import com.hbm.tileentity.bomb.TileEntityNukeFleija;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;



@Mixin(TileEntityNukeFleija.class)
public abstract class MixinTileEntityNukeFleija {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
