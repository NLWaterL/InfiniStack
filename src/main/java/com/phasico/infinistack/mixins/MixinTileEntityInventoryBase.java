package com.phasico.infinistack.mixins;

import com.hbm.tileentity.TileEntityInventoryBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.phasico.infinistack.helper.Configurables;


@Mixin(TileEntityInventoryBase.class)
public abstract class MixinTileEntityInventoryBase {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
