package com.phasico.infinistack.mixins.hbm;

import com.hbm.tileentity.machine.storage.TileEntityCrateBase;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TileEntityCrateBase.class)
public abstract class MixinTileEntityCrateBase {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
