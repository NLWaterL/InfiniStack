package com.phasico.infinistack.mixins;

import com.hbm.tileentity.machine.rbmk.TileEntityRBMKSlottedBase;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;



@Mixin(TileEntityRBMKSlottedBase.class)
public abstract class MixinTileEntityRBMKSlottedBase {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
