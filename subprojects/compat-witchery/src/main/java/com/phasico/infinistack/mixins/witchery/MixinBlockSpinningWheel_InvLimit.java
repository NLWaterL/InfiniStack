package com.phasico.infinistack.mixins.witchery;

import com.emoniph.witchery.blocks.BlockSpinningWheel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(BlockSpinningWheel.class)
@Pseudo
public abstract class MixinBlockSpinningWheel {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
