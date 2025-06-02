package com.phasico.infinistack.mixins.avaritia;

import fox.spiteful.avaritia.tile.TileEntityDireCrafting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityDireCrafting.class)
public abstract class MixinTileEntityDireCrafting {

    @Overwrite(remap = false)
    public int func_70297_j_(){
        return Configurables.maxStackSize;
    }
}
