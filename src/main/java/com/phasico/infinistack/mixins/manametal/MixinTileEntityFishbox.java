package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.produce.fishing.TileEntityFishbox; // produce\fishing\TileEntityFishbox.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityFishbox.class)
public abstract class MixinTileEntityFishbox {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}