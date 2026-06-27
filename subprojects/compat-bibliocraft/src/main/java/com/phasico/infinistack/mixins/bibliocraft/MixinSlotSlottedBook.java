package com.phasico.infinistack.mixins.bibliocraft;

import jds.bibliocraft.items.SlotSlottedBook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(SlotSlottedBook.class)
@Pseudo
public abstract class MixinSlotSlottedBook {

    @Overwrite(remap = false)
    public int func_75219_a() {
        return Configurables.maxStackSize;
    }

}
