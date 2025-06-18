package com.phasico.infinistack.mixins.buildcraft;

import buildcraft.core.lib.engines.TileEngineWithInventory;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TileEngineWithInventory.class)
public abstract class MixinTileEngineWithInventory {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
