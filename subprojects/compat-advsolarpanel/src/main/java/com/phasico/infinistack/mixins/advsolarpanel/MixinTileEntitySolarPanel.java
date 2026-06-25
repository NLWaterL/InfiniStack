package com.phasico.infinistack.mixins.advsolarpanel;

import advsolar.common.tiles.TileEntitySolarPanel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntitySolarPanel.class)
@Pseudo
public abstract class MixinTileEntitySolarPanel {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
