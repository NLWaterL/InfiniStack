package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.produce.brewing.TileEntityAdvancedBrewing; // produce\brewing\TileEntityAdvancedBrewing.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityAdvancedBrewing.class)
public abstract class MixinTileEntityAdvancedBrewing {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}