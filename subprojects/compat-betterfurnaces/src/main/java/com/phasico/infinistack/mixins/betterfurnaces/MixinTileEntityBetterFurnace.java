package com.phasico.infinistack.mixins.betterfurnaces;

import at.flabs.betterfurnaces.core.TileEntityBetterFurnace; // at\flabs\betterfurnaces\core\TileEntityBetterFurnace.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityBetterFurnace.class)
@Pseudo
public abstract class MixinTileEntityBetterFurnace {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
