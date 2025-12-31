package com.phasico.infinistack.mixins;

import net.minecraft.inventory.InventoryCrafting;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(InventoryCrafting.class)
public abstract class MixinInventoryCrafting {

    @Overwrite
    public int getInventoryStackLimit()
    {
        return Configurables.enableFastCraft ? Configurables.maxStackSize : 1024;
    }
}
