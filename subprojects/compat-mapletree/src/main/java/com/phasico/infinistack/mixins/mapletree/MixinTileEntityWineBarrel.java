package com.phasico.infinistack.mixins.mapletree;

import ecru.MapleTree.tile.ecru_TileEntityWineBarrel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(ecru_TileEntityWineBarrel.class)
@Pseudo
public abstract class MixinTileEntityWineBarrel {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
