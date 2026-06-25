package com.phasico.infinistack.mixins.bibliocraft;

import jds.bibliocraft.tileentities.TileEntityDinnerPlate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityDinnerPlate.class)
@Pseudo
public abstract class MixinTileEntityDinnerPlate {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
