package com.phasico.infinistack.mixins.hbm;

import com.hbm.tileentity.TileEntityInventoryBase; // hbm\tileentity\TileEntityInventoryBase.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityInventoryBase.class)
@Pseudo
public abstract class MixinTileEntityInventoryBase {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}