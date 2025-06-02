package com.phasico.infinistack.mixins.projecte;

import moze_intel.projecte.gameObjs.container.inventory.MercurialEyeInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(MercurialEyeInventory.class)
public abstract class MixinMercurialEyeInventory {

    @Overwrite(remap = false)
    public int func_70297_j_()
    {
        return Configurables.maxStackSize;
    }

}
