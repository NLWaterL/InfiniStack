package com.phasico.infinistack.mixins.hbm;

import com.hbm.tileentity.machine.TileEntityFoundryCastingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityFoundryCastingBase.class)
public abstract class MixinTileEntityFoundryCastingBase {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
