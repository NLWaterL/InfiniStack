package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.tileentity.TileEntityRFMakeMana; // tileentity\TileEntityRFMakeMana.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityRFMakeMana.class)
public abstract class MixinTileEntityRFMakeMana {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}