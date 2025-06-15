package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.produce.casting.TileEntityCastingOther; // produce\casting\TileEntityCastingOther.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityCastingOther.class)
public abstract class MixinTileEntityCastingOther {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}