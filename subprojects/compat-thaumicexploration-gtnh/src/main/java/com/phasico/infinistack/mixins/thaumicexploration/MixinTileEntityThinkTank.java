package com.phasico.infinistack.mixins.thaumicexploration;

import flaxbeard.thaumicexploration.tile.TileEntityThinkTank;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityThinkTank.class)
@Pseudo
public abstract class MixinTileEntityThinkTank {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
