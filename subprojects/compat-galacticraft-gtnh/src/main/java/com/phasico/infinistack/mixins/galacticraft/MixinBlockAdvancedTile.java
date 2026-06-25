package com.phasico.infinistack.mixins.galacticraft;

import micdoodle8.mods.galacticraft.core.blocks.BlockAdvancedTile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(BlockAdvancedTile.class)
@Pseudo
public abstract class MixinBlockAdvancedTile {

    @Redirect(
            method = "dropEntireInventory",
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
