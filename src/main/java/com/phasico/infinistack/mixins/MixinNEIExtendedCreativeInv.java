package com.phasico.infinistack.mixins;

import codechicken.nei.ExtendedCreativeInv;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(ExtendedCreativeInv.class)
public abstract class MixinNEIExtendedCreativeInv {

    @Overwrite
    public int getInventoryStackLimit() {
        return Configurables.maxStackSize;
    }

}
