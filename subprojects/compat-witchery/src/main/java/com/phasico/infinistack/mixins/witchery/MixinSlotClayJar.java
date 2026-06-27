package com.phasico.infinistack.mixins.witchery;

import com.emoniph.witchery.util.SlotClayJar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(SlotClayJar.class)
@Pseudo
public abstract class MixinSlotClayJar {

    @Overwrite(remap = false)
    public int func_75219_a() {
        return Configurables.maxStackSize;
    }

}
