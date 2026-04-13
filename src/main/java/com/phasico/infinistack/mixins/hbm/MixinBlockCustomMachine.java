package com.phasico.infinistack.mixins.hbm;

import com.hbm.blocks.machine.BlockCustomMachine; // hbm\blocks\machine\BlockCustomMachine.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(BlockCustomMachine.class)
@Pseudo
public abstract class MixinBlockCustomMachine {

    @Redirect(
            method = "func_149749_a",
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