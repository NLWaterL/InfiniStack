package com.phasico.infinistack.mixins.botania;

import vazkii.botania.common.block.corporea.BlockCorporeaBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(BlockCorporeaBase.class)
@Pseudo
public abstract class MixinBlockCorporeaBase {

    @Redirect(
            method = "func_149749_a",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I"
            ), remap = false
    )
    private int noStackSplit(Random random, int bound) {
        if (bound == 21) {
            return Integer.MAX_VALUE - 10;
        }
        return random.nextInt(bound);
    }

}
