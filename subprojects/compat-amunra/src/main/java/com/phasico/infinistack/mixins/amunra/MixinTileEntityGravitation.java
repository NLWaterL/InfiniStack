package com.phasico.infinistack.mixins.amunra;

import de.katzenpapst.amunra.tile.TileEntityGravitation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityGravitation.class)
@Pseudo
public abstract class MixinTileEntityGravitation {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
