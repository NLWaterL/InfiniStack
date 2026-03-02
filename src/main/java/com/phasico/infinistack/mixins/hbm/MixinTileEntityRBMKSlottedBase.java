package com.phasico.infinistack.mixins.hbm;

import com.hbm.tileentity.machine.rbmk.TileEntityRBMKSlottedBase; // hbm\tileentity\machine\rbmk\TileEntityRBMKSlottedBase.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityRBMKSlottedBase.class)
@Pseudo
public abstract class MixinTileEntityRBMKSlottedBase {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}