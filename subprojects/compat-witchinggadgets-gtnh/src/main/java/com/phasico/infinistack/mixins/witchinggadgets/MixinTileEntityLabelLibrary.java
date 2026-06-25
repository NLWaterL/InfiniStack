package com.phasico.infinistack.mixins.witchinggadgets;

import witchinggadgets.common.blocks.tiles.TileEntityLabelLibrary;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityLabelLibrary.class)
@Pseudo
public abstract class MixinTileEntityLabelLibrary {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
