package com.phasico.infinistack.mixins.daoza;

import com.daozcraft.itemsSP.ZipCard;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = ZipCard.class, remap = false)
@Pseudo
public abstract class MixinZipCard {

    @ModifyArg(
            method = "func_77659_a",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/Math;min(II)I",
                    ordinal = 0
            ),
            index = 0, remap = false)
    public int modifyStackSizeCap(int original){
        return Configurables.maxStackSize;
    }

}
