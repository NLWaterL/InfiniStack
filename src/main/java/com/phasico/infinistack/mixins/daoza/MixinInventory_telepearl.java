package com.phasico.infinistack.mixins.daoza;

import container.Inventory_telepearl; // container\Inventory_telepearl.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(Inventory_telepearl.class)
public abstract class MixinInventory_telepearl {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}