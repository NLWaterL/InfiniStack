package com.phasico.infinistack.mixins;

import net.minecraft.inventory.InventoryMerchant;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(InventoryMerchant.class)
public abstract class MixinInventoryMerchant {

    @Overwrite
    public int getInventoryStackLimit()
    {
        return Configurables.maxStackSize;
    }
}
