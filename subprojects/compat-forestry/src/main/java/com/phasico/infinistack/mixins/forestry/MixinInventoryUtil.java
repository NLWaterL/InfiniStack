package com.phasico.infinistack.mixins.forestry;

import com.phasico.infinistack.helper.Configurables;
import forestry.core.utils.InventoryUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;
import java.util.Random;

@Mixin(InventoryUtil.class)
@Pseudo
public abstract class MixinInventoryUtil {

    @Redirect(
            method = "dropItemStackFromInventory",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I"
            ), remap = false
    )
    private static int noStackSplit(Random random, int bound) {
        if (bound == 21) {
            return Integer.MAX_VALUE - 10;
        }
        return random.nextInt(bound);
    }

    @ModifyConstant(
            method = "containsPercent(Lnet/minecraft/inventory/IInventory;FII)Z",
            constant = @Constant(intValue = 64),
            remap = false
    )
    private static int replaceItemStackLimit(int original){
        return Configurables.maxStackSize;
    }

}
