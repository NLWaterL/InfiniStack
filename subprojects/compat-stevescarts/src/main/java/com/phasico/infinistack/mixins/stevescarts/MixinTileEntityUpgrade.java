package com.phasico.infinistack.mixins.stevescarts;

import vswe.stevescarts.TileEntities.TileEntityUpgrade;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityUpgrade.class)
@Pseudo
public abstract class MixinTileEntityUpgrade {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
