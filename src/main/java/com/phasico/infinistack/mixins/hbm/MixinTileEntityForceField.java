package com.phasico.infinistack.mixins.hbm;

import com.hbm.tileentity.machine.TileEntityForceField;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.phasico.infinistack.helper.Configurables;


@Mixin(TileEntityForceField.class)
public abstract class MixinTileEntityForceField {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
