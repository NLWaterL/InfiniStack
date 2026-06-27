package com.phasico.infinistack.mixins.railcraft;

import mods.railcraft.common.gui.slots.SlotStackFilter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(SlotStackFilter.class)
@Pseudo
public abstract class MixinSlotStackFilter {

    @Overwrite(remap = false)
    public int func_75219_a() {
        return Configurables.maxStackSize;
    }

}
