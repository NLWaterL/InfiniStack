package com.phasico.infinistack.mixins;

import net.minecraft.tileentity.TileEntityBrewingStand;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TileEntityBrewingStand.class)
public abstract class MixinTileEntityBrewingStand {

    @Overwrite
    public int getInventoryStackLimit()
    {
        return Configurables.maxStackSize;
    }
}
