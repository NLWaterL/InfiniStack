package com.phasico.infinistack.mixins.stevescarts;

import vswe.stevescarts.Slots.SlotAssemblerFuel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(SlotAssemblerFuel.class)
@Pseudo
public abstract class MixinSlotAssemblerFuel {

    @Overwrite(remap = false)
    public int func_75219_a() {
        return Configurables.maxStackSize;
    }

}
