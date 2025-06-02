package com.phasico.infinistack.mixins.avaritia;

import fox.spiteful.avaritia.tile.TileEntityAutoDireCrafting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityAutoDireCrafting.class)
public abstract class MixinTileEntityAutoDireCrafting {

    @Overwrite(remap = false)
    public int func_70297_j_(){
        return Configurables.maxStackSize;
    }
}
