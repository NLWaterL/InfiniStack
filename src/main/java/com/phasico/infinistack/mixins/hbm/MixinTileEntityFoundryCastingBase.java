package com.phasico.infinistack.mixins.hbm;

import com.hbm.tileentity.machine.TileEntityFoundryCastingBase; // hbm\tileentity\machine\TileEntityFoundryCastingBase.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityFoundryCastingBase.class)
@Pseudo
public abstract class MixinTileEntityFoundryCastingBase {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}