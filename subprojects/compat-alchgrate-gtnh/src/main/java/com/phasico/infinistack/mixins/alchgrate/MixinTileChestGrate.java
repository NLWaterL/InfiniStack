package com.phasico.infinistack.mixins.alchgrate;

import com.technicianlp.chestgrate.TileChestGrate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileChestGrate.class)
@Pseudo
public abstract class MixinTileChestGrate {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
