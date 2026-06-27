package com.phasico.infinistack.mixins.bibliocraft;

import jds.bibliocraft.blocks.SlotCookie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(SlotCookie.class)
@Pseudo
public abstract class MixinSlotCookie {

    @Overwrite(remap = false)
    public int func_75219_a() {
        return Configurables.maxStackSize;
    }

}
