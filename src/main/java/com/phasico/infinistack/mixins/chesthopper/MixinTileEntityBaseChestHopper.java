package com.phasico.infinistack.mixins.chesthopper;

import com.simplysimon.chesthopper.block.TileEntityBaseChestHopper; // com\simplysimon\chesthopper\block\TileEntityBaseChestHopper.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityBaseChestHopper.class)
public abstract class MixinTileEntityBaseChestHopper {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}