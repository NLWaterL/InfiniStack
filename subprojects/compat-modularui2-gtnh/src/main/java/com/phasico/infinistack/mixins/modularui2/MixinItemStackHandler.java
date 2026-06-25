package com.phasico.infinistack.mixins.modularui2;

import com.cleanroommc.modularui.utils.item.ItemStackHandler;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

@Mixin(ItemStackHandler.class)
@Pseudo
public abstract class MixinItemStackHandler {

    @Overwrite(remap = false)
    public int getSlotLimit(int slot) {
        return Configurables.maxStackSize;
    }
}
