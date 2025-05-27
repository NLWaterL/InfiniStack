package com.phasico.infinistack.mixins;

import com.hbm.tileentity.machine.TileEntityMachineSiren;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;



@Mixin(TileEntityMachineSiren.class)
public abstract class MixinTileEntityMachineSiren {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
