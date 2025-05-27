package com.phasico.infinistack.mixins.hbm;

import com.hbm.tileentity.machine.TileEntityRtgFurnace;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

@Pseudo
@Mixin(TileEntityRtgFurnace.class)
public abstract class MixinTileEntityRtgFurnace {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
