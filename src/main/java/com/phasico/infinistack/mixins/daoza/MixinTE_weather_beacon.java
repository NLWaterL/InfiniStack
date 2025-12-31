package com.phasico.infinistack.mixins.daoza;

import teplus.TE_weather_beacon; // teplus\TE_weather_beacon.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TE_weather_beacon.class)
public abstract class MixinTE_weather_beacon {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}