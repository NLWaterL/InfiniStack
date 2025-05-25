package com.phasico.infinistack.mixins;

import net.minecraft.tileentity.TileEntityChest;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TileEntityChest.class)
public abstract class MixinTileEntityChest {

    @Overwrite
    public int getInventoryStackLimit()
    {
        return Configurables.maxStackSize;
    }
}
