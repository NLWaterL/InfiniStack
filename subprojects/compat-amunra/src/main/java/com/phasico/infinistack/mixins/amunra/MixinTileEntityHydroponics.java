package com.phasico.infinistack.mixins.amunra;

import de.katzenpapst.amunra.tile.TileEntityHydroponics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityHydroponics.class)
@Pseudo
public abstract class MixinTileEntityHydroponics {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
