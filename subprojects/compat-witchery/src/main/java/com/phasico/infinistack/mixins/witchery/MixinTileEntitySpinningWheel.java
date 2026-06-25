package com.phasico.infinistack.mixins.witchery;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(targets = "com.emoniph.witchery.blocks.BlockSpinningWheel$TileEntitySpinningWheel")
@Pseudo
public abstract class MixinTileEntitySpinningWheel {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
