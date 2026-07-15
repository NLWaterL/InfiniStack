package com.phasico.infinistack.mixins.daoza;

import com.daozcraft.itemsSP.ZipCard;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ZipCard.class)
@Pseudo
public abstract class MixinZipCard {

    @ModifyConstant(method = "func_77659_a", constant = @Constant(intValue = 64), remap = false)
    public int modifyStackSizeCap(int original){
        return Configurables.maxStackSize;
    }

}
