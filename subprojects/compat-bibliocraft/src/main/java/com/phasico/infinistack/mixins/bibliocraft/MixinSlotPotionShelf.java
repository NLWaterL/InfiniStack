package com.phasico.infinistack.mixins.bibliocraft;

import jds.bibliocraft.blocks.SlotPotionShelf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(SlotPotionShelf.class)
@Pseudo
public abstract class MixinSlotPotionShelf {

    @Overwrite(remap = false)
    public int func_75219_a() {
        return Configurables.maxStackSize;
    }

}
