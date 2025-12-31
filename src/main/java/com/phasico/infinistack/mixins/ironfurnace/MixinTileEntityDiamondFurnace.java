package com.phasico.infinistack.mixins.ironfurnace;

import xenopack.tileentity.TileEntityDiamondFurnace; // tileentity\TileEntityDiamondFurnace.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityDiamondFurnace.class)
public abstract class MixinTileEntityDiamondFurnace {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}