package com.phasico.infinistack.mixins.ae2;

import appeng.tile.AEBaseInvTile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(AEBaseInvTile.class)
@Pseudo
public abstract class MixinAEBaseInvTile {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
