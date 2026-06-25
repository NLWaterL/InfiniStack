package com.phasico.infinistack.mixins.bloodmagic;

import WayofTime.alchemicalWizardry.common.tileEntity.TEInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TEInventory.class)
@Pseudo
public abstract class MixinTEInventory {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
