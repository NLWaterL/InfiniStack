package com.phasico.infinistack.mixins.gregtech;

import bartworks.common.tileentities.classic.TileEntityHeatedWaterPump; // bartworks\common\tileentities\classic\TileEntityHeatedWaterPump.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityHeatedWaterPump.class)
@Pseudo
public abstract class MixinTileEntityHeatedWaterPump {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}