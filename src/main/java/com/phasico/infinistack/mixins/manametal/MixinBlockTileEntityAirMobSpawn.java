package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.blocks.BlockTileEntityAirMobSpawn; // blocks\BlockTileEntityAirMobSpawn.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Pseudo
@Mixin(BlockTileEntityAirMobSpawn.class)
public abstract class MixinBlockTileEntityAirMobSpawn {

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