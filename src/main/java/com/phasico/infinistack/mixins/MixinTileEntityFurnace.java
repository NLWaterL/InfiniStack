package com.phasico.infinistack.mixins;

import net.minecraft.tileentity.TileEntityFurnace;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TileEntityFurnace.class)
public abstract class MixinTileEntityFurnace {

    @Overwrite
    public int getInventoryStackLimit()
    {
        return Configurables.maxStackSize;
    }
}
