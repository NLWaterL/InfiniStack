package com.phasico.infinistack.mixins.gregtech;

import com.phasico.infinistack.helper.Configurables;
import gtPlusPlus.core.slots.SlotIntegratedCircuit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

@Mixin(SlotIntegratedCircuit.class)
@Pseudo
public abstract class MixinSlotIntegratedCircuit {

    @Overwrite(remap = false)
    public int func_75219_a() {
        return Configurables.maxStackSize;
    }

}
