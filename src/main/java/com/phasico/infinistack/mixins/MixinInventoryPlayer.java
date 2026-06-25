package com.phasico.infinistack.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import net.minecraft.entity.player.InventoryPlayer;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryPlayer.class)
public abstract class MixinInventoryPlayer {

    @Overwrite
    public int getInventoryStackLimit()
    {
        return Configurables.maxStackSize;
    }
}
