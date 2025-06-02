package com.phasico.infinistack.mixins.projecte;

import moze_intel.projecte.gameObjs.container.inventory.EternalDensityInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(EternalDensityInventory.class)
public abstract class MixinEternalDensityInventory {

    @Overwrite(remap = false)
    public int func_70297_j_()
    {
        return Configurables.maxStackSize;
    }

}
