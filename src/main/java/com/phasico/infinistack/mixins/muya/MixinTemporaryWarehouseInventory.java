package com.phasico.infinistack.mixins.muya;

import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(targets = "tw.pearki.mcmod.muya.nbt.api.EntityNBTTemporaryWarehouse$TemporaryWarehouseInventory")
public abstract class MixinTemporaryWarehouseInventory {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }
}
