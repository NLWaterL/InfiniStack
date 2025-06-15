package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.produce.gemcraft.TileEntityGemIdentification; // produce\gemcraft\TileEntityGemIdentification.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityGemIdentification.class)
public abstract class MixinTileEntityGemIdentification {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}