package com.phasico.infinistack.mixins.cofhcore;

import cofh.lib.inventory.InventoryCraftingCustom;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryCraftingCustom.class)
@Pseudo
public abstract class MixinInventoryCraftingCustom {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
