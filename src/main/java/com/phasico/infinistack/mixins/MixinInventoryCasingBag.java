package com.phasico.infinistack.mixins;

import com.hbm.items.tool.ItemCasingBag.InventoryCasingBag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.phasico.infinistack.helper.Configurables;


@Mixin(InventoryCasingBag.class)
public abstract class MixinInventoryCasingBag {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
