package com.phasico.infinistack.mixins.daoza;

import container.Inventory_book; // container\Inventory_book.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(Inventory_book.class)
public abstract class MixinInventory_book {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}