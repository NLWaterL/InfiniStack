package com.phasico.infinistack.mixins.compactstorage;

import com.phasico.infinistack.helper.Configurables;
import com.tattyseal.compactstorage.tileentity.TileEntityChestBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TileEntityChestBuilder.class)
public abstract class MixinTileEntityChestBuilder {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
