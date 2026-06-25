package com.phasico.infinistack.mixins.railcraft;

import mods.railcraft.common.blocks.machine.TileMultiBlockInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileMultiBlockInventory.class)
@Pseudo
public abstract class MixinTileMultiBlockInventory {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
