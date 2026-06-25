package com.phasico.infinistack.mixins.draconic;

import com.brandon3055.draconicevolution.common.tileentities.TileUpgradeModifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileUpgradeModifier.class)
@Pseudo
public abstract class MixinTileUpgradeModifier {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
