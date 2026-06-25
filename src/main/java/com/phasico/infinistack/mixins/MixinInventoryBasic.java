package com.phasico.infinistack.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import net.minecraft.inventory.InventoryBasic;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryBasic.class)
public abstract class MixinInventoryBasic {

    @Overwrite
    public int getInventoryStackLimit()
    {
        return Configurables.maxStackSize;
    }
}
