package com.phasico.infinistack.mixins.hbm;

import com.hbm.items.tool.ItemAmmoBag.InventoryAmmoBag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.phasico.infinistack.helper.Configurables;


@Mixin(InventoryAmmoBag.class)
public abstract class MixinInventoryAmmoBag {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
