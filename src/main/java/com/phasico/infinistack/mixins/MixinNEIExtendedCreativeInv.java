package com.phasico.infinistack.mixins;

import codechicken.nei.ExtendedCreativeInv;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;

import com.phasico.infinistack.helper.Configurables;


@Mixin(ExtendedCreativeInv.class)
@Pseudo
public abstract class MixinNEIExtendedCreativeInv {

    @Overwrite
    public int getInventoryStackLimit() {
        return Configurables.maxStackSize;
    }

}
