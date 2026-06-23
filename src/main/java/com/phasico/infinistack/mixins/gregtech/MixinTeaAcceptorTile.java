package com.phasico.infinistack.mixins.gregtech;

import kubatech.tileentity.TeaAcceptorTile; // kubatech\tileentity\TeaAcceptorTile.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TeaAcceptorTile.class)
@Pseudo
public abstract class MixinTeaAcceptorTile {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}