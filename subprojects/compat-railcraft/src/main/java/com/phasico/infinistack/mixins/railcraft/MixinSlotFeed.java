package com.phasico.infinistack.mixins.railcraft;

import mods.railcraft.common.gui.slots.SlotFeed;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(SlotFeed.class)
@Pseudo
public abstract class MixinSlotFeed {

    @Overwrite(remap = false)
    public int func_75219_a() {
        return Configurables.maxStackSize;
    }

}
