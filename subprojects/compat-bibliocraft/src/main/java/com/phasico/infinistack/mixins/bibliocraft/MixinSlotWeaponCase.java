package com.phasico.infinistack.mixins.bibliocraft;

import jds.bibliocraft.blocks.SlotWeaponCase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(SlotWeaponCase.class)
@Pseudo
public abstract class MixinSlotWeaponCase {

    @Overwrite(remap = false)
    public int func_75219_a() {
        return Configurables.maxStackSize;
    }

}
