package com.phasico.infinistack.mixins.gregtech;

import gregtech.api.metatileentity.BaseMetaPipeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import com.phasico.infinistack.helper.Configurables;

@Mixin(BaseMetaPipeEntity.class)
@Pseudo
public abstract class MixinBaseMetaPipeEntity {

    @ModifyConstant(
            method = "func_70297_j_",
            constant = @Constant(intValue = 64),
            remap = false)
    public int replaceInventoryStackLimit(int original) {
        return Configurables.maxStackSize;
    }

}
