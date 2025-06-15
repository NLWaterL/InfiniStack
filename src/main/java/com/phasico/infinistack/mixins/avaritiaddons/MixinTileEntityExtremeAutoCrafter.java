package com.phasico.infinistack.mixins.avaritiaddons;

import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import wanion.avaritiaddons.block.extremeautocrafter.TileEntityExtremeAutoCrafter;

@Mixin(TileEntityExtremeAutoCrafter.class)
public abstract class MixinTileEntityExtremeAutoCrafter {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
