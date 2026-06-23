package com.phasico.infinistack.mixins.gregtech;

import gregtech.common.covers.CoverItemMeter;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CoverItemMeter.class)
@Pseudo
public abstract class MixinCoverItemMeter {

    @Redirect(
            method = "computeSignalBasedOnItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;func_77976_d()I"
            ),
            remap = false
    )
    private static int capMaxStackSizeForSignal(ItemStack stack) {
        return Math.min(stack.getMaxStackSize(), 64);
    }

}
