package com.phasico.infinistack.mixins;

import net.minecraft.tileentity.TileEntityDispenser;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TileEntityDispenser.class)
public abstract class MixinTileEntityDispenser {

    @Overwrite
    public int getInventoryStackLimit()
    {
        return Configurables.maxStackSize;
    }
}
