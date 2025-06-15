package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.produce.brewing.TileEntityPotionMake; // produce\brewing\TileEntityPotionMake.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityPotionMake.class)
public abstract class MixinTileEntityPotionMake {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}