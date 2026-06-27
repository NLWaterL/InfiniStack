package com.phasico.infinistack.mixins.computronics;

import pl.asie.computronics.integration.railcraft.gui.slot.SlotSecureInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(SlotSecureInput.class)
@Pseudo
public abstract class MixinSlotSecureInput {

    @Overwrite(remap = false)
    public int func_75219_a() {
        return Configurables.maxStackSize;
    }

}
