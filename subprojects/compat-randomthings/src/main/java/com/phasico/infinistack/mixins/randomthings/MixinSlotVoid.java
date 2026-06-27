package com.phasico.infinistack.mixins.randomthings;

import lumien.randomthings.Container.Slots.SlotVoid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(SlotVoid.class)
@Pseudo
public abstract class MixinSlotVoid {

    @Overwrite(remap = false)
    public int func_75219_a() {
        return Configurables.maxStackSize;
    }

}
