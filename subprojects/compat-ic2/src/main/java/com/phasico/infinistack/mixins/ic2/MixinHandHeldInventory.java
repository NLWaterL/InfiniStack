package com.phasico.infinistack.mixins.ic2;

import ic2.core.item.tool.HandHeldInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(HandHeldInventory.class)
@Pseudo
public abstract class MixinHandHeldInventory {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
