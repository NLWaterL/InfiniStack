package com.phasico.infinistack.mixins.ironfurnace;

import xenopack.tileentity.TileEntityObsidianFurnace; // tileentity\TileEntityObsidianFurnace.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityObsidianFurnace.class)
public abstract class MixinTileEntityObsidianFurnace {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}