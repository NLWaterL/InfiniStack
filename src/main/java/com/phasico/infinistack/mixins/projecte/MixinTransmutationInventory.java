package com.phasico.infinistack.mixins.projecte;

import com.phasico.infinistack.helper.Configurables;
import moze_intel.projecte.gameObjs.container.inventory.TransmutationInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TransmutationInventory.class)
public abstract class MixinTransmutationInventory {

    //Well, transmutation table just eat EMC if it exceeds it's limit (which is not high enough)

    //Also, it cause so much lag.

    @Overwrite(remap = false)
    public int func_70297_j_()
    {
        return 1000;
    }

}
