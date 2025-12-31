package com.phasico.infinistack.mixins.projecte;

import com.phasico.infinistack.helper.Configurables;
import moze_intel.projecte.gameObjs.container.TransmutationContainer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TransmutationContainer.class)
public abstract class MixinTransmutationContainer {

    @Redirect(
            method = "func_82846_b(Lnet/minecraft/entity/player/EntityPlayer;I)Lnet/minecraft/item/ItemStack;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;func_77976_d()I"
            ),
            remap = false
    )
    private int capShiftClickStackSize(ItemStack instance) {
        return Math.min(Configurables.transmutationLimit, instance.getMaxStackSize());
    }
}
