package com.phasico.infinistack.mixins;

import net.minecraft.tileentity.TileEntityHopper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityHopper.class)
public abstract class MixinTileEntityHopper {

    @Overwrite
    public int getInventoryStackLimit()
    {
        return Configurables.maxStackSize;
    }
}
