package com.phasico.infinistack.mixins.ironfurnace;

import xenopack.tileentity.TileEntityCrystalFurnace; // tileentity\TileEntityCrystalFurnace.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityCrystalFurnace.class)
public abstract class MixinTileEntityCrystalFurnace {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}