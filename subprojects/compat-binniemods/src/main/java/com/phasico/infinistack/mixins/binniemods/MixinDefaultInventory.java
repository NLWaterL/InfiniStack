package com.phasico.infinistack.mixins.binniemods;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(targets = "binnie.core.machines.base.DefaultInventory")
@Pseudo
public abstract class MixinDefaultInventory {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
