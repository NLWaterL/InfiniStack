package com.phasico.infinistack.mixins.projecte;

import moze_intel.projecte.utils.WorldHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Random;

@Mixin(WorldHelper.class)
public abstract class MixinWorldHelper {

    @Redirect(
            method = "spawnEntityItem",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I"
            ),remap = false
    )
    private static int noStackSplit(Random random, int bound) {
        if (bound == 21) {
            return Integer.MAX_VALUE - 10;
        }
        return random.nextInt(bound);
    }

}
