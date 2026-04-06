package com.phasico.infinistack.mixins.projecte;

import moze_intel.projecte.gameObjs.container.AlchBagInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(AlchBagInventory.class)
@Pseudo
public abstract class MixinFMPEAlchBagInventory {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}