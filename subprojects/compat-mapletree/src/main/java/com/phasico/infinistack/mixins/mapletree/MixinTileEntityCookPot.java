package com.phasico.infinistack.mixins.mapletree;

import ecru.MapleTree.tile.ecru_TileEntityCookPot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(ecru_TileEntityCookPot.class)
@Pseudo
public abstract class MixinTileEntityCookPot {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
