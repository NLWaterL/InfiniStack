package com.phasico.infinistack.mixins;

import com.hbm.tileentity.machine.TileEntityMachineRTG;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.phasico.infinistack.helper.Configurables;


@Mixin(TileEntityMachineRTG.class)
public abstract class MixinTileEntityMachineRTG {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
