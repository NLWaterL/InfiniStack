package com.phasico.infinistack.mixins.buildcraft;

import buildcraft.core.lib.inventory.InventoryConcatenator;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(InventoryConcatenator.class)
public abstract class MixinInventoryConcatenator {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
