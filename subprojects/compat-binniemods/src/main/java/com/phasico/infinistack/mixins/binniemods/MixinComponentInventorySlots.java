package com.phasico.infinistack.mixins.binniemods;

import binnie.core.machines.inventory.ComponentInventorySlots;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(ComponentInventorySlots.class)
@Pseudo
public abstract class MixinComponentInventorySlots {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
