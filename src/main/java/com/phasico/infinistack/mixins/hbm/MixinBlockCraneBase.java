package com.phasico.infinistack.mixins.hbm;

import com.hbm.blocks.network.BlockCraneBase; // hbm\blocks\network\BlockCraneBase.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(BlockCraneBase.class)
@Pseudo
public abstract class MixinBlockCraneBase {

    @Redirect(
            method = "dropContents",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I"
            ), remap = false
    )
    private int noStackSplit(Random random, int bound) {
        if (bound == 21) {
            return Integer.MAX_VALUE;
        }
        return random.nextInt(bound);
    }

}