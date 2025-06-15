package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.items.armor.TileEntityArmorstrengthen; // items\armor\TileEntityArmorstrengthen.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityArmorstrengthen.class)
public abstract class MixinTileEntityArmorstrengthen {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}