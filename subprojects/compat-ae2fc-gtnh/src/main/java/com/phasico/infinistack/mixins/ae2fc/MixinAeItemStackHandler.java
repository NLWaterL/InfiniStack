package com.phasico.infinistack.mixins.ae2fc;

import com.glodblock.github.inventory.AeItemStackHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(AeItemStackHandler.class)
@Pseudo
public abstract class MixinAeItemStackHandler {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
