package com.phasico.infinistack.mixins.railcraft;

import mods.railcraft.common.blocks.machine.beta.TileBoilerFirebox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileBoilerFirebox.class)
@Pseudo
public abstract class MixinTileBoilerFirebox {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
