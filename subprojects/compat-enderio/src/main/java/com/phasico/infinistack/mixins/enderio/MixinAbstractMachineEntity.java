package com.phasico.infinistack.mixins.enderio;

import crazypants.enderio.machine.AbstractMachineEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(AbstractMachineEntity.class)
@Pseudo
public abstract class MixinAbstractMachineEntity {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
