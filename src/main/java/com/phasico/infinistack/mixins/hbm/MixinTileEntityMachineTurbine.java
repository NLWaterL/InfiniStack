package com.phasico.infinistack.mixins.hbm;

import com.hbm.tileentity.machine.TileEntityMachineTurbine; // hbm\tileentity\machine\TileEntityMachineTurbine.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityMachineTurbine.class)
@Pseudo
public abstract class MixinTileEntityMachineTurbine {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}