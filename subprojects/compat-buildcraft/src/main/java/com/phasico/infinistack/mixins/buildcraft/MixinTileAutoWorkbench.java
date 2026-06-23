package com.phasico.infinistack.mixins.buildcraft;

import buildcraft.factory.TileAutoWorkbench;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;



@Mixin(TileAutoWorkbench.class)
@Pseudo
public abstract class MixinTileAutoWorkbench {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
