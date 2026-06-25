package com.phasico.infinistack.mixins.thaumichorizons;

import com.kentington.thaumichorizons.common.tiles.TileSoulExtractor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileSoulExtractor.class)
@Pseudo
public abstract class MixinTileSoulExtractor {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
