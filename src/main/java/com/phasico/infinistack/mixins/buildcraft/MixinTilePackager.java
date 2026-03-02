package com.phasico.infinistack.mixins.buildcraft;

import buildcraft.silicon.TilePackager;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;



@Mixin(TilePackager.class)
@Pseudo
public abstract class MixinTilePackager {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
