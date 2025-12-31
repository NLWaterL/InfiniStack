package com.phasico.infinistack.mixins.ironfurnace;

import xenopack.tileentity.TileEntityGoldFurnace; // tileentity\TileEntityGoldFurnace.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityGoldFurnace.class)
public abstract class MixinTileEntityGoldFurnace {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}