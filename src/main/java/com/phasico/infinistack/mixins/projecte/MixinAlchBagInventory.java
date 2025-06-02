package com.phasico.infinistack.mixins.projecte;

import moze_intel.projecte.gameObjs.container.inventory.AlchBagInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(AlchBagInventory.class)
public abstract class MixinAlchBagInventory {

    @Overwrite(remap = false)
    public int func_70297_j_()
    {
        return Configurables.maxStackSize;
    }

}
