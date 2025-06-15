package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.furniture.TileEntityJabbaBox; // furniture\TileEntityJabbaBox.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityJabbaBox.class)
public abstract class MixinTileEntityJabbaBox {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}