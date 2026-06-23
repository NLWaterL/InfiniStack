package com.phasico.infinistack.mixins.gregtech;

import gregtech.api.metatileentity.CommonMetaTileEntity; // gregtech\api\metatileentity\CommonMetaTileEntity.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(CommonMetaTileEntity.class)
@Pseudo
public abstract class MixinCommonMetaTileEntity {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}