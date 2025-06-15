package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.produce.gemcraft.TileEntityGemCraft; // produce\gemcraft\TileEntityGemCraft.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityGemCraft.class)
public abstract class MixinTileEntityGemCraft {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}