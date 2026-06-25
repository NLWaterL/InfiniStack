package com.phasico.infinistack.mixins.bloodmagic;

import WayofTime.alchemicalWizardry.common.tileEntity.TEAlchemicCalcinator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TEAlchemicCalcinator.class)
@Pseudo
public abstract class MixinTEAlchemicCalcinator {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
