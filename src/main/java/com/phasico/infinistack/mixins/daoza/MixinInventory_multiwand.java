package com.phasico.infinistack.mixins.daoza;

import container.Inventory_multiwand; // container\Inventory_multiwand.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(Inventory_multiwand.class)
public abstract class MixinInventory_multiwand {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}