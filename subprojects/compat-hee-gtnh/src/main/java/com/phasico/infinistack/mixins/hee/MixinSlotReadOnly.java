package com.phasico.infinistack.mixins.hee;

import chylex.hee.gui.slots.SlotReadOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(SlotReadOnly.class)
@Pseudo
public abstract class MixinSlotReadOnly {

    @Overwrite(remap = false)
    public int func_75219_a() {
        return Configurables.maxStackSize;
    }

}
