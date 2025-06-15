package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.dark_magic.TileEntityDarkSteelFurnace; // dark_magic\TileEntityDarkSteelFurnace.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityDarkSteelFurnace.class)
public abstract class MixinTileEntityDarkSteelFurnace {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}