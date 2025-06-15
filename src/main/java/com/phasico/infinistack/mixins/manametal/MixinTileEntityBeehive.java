package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.produce.beekeeping.TileEntityBeehive; // produce\beekeeping\TileEntityBeehive.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityBeehive.class)
public abstract class MixinTileEntityBeehive {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}