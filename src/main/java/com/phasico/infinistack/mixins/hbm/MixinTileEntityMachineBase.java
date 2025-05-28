package com.phasico.infinistack.mixins.hbm;

import com.hbm.tileentity.TileEntityMachineBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;

import com.phasico.infinistack.helper.Configurables;


@Pseudo
@Mixin(TileEntityMachineBase.class)
public abstract class MixinTileEntityMachineBase {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
