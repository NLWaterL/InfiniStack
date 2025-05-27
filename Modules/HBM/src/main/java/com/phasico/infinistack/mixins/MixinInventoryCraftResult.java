package com.phasico.infinistack.mixins;

import net.minecraft.inventory.InventoryCraftResult;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(InventoryCraftResult.class)
public abstract class MixinInventoryCraftResult {

    @Overwrite
    public int getInventoryStackLimit()
    {
        return Configurables.maxStackSize;
    }
}
