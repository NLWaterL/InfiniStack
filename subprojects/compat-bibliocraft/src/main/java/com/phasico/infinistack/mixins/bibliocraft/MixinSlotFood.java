package com.phasico.infinistack.mixins.bibliocraft;

import jds.bibliocraft.blocks.SlotFood;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(SlotFood.class)
@Pseudo
public abstract class MixinSlotFood {

    @Overwrite(remap = false)
    public int func_75219_a() {
        return Configurables.maxStackSize;
    }

}
