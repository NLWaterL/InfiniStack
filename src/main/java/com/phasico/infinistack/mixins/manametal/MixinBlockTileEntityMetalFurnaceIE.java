package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.api.addon.BlockTileEntityMetalFurnaceIE; // api\addon\BlockTileEntityMetalFurnaceIE.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Pseudo
@Mixin(BlockTileEntityMetalFurnaceIE.class)
public abstract class MixinBlockTileEntityMetalFurnaceIE {

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