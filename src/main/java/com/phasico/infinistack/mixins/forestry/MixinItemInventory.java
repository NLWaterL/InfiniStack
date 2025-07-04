package com.phasico.infinistack.mixins.forestry;

import forestry.core.inventory.ItemInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(ItemInventory.class)
public abstract class MixinItemInventory {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
