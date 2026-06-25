package com.phasico.infinistack.mixins.draconic;

import com.brandon3055.draconicevolution.common.tileentities.TileDissEnchanter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileDissEnchanter.class)
@Pseudo
public abstract class MixinTileDissEnchanter {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
