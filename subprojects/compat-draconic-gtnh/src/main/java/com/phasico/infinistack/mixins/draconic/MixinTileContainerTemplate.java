package com.phasico.infinistack.mixins.draconic;

import com.brandon3055.draconicevolution.common.tileentities.TileContainerTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileContainerTemplate.class)
@Pseudo
public abstract class MixinTileContainerTemplate {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
