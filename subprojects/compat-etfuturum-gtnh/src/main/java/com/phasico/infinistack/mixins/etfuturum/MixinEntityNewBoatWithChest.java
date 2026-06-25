package com.phasico.infinistack.mixins.etfuturum;

import ganymedes01.etfuturum.entities.EntityNewBoatWithChest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import com.phasico.infinistack.helper.Configurables;
import java.util.Random;

@Mixin(EntityNewBoatWithChest.class)
@Pseudo
public abstract class MixinEntityNewBoatWithChest {

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

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
