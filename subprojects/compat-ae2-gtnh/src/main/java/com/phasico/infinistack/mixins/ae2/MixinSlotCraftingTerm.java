package com.phasico.infinistack.mixins.ae2;

import appeng.container.slot.SlotCraftingTerm;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.phasico.infinistack.helper.Configurables;

@Mixin(SlotCraftingTerm.class)
@Pseudo
public abstract class MixinSlotCraftingTerm {

    @Redirect(
        method = "doClick",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;func_77976_d()I"),
        remap = false
    )
    private int redirectCraftClickMaxStack(ItemStack stack) {
        return Configurables.maxStackSize;
    }

    @Inject(
        method = "capCraftingAttempts",
        at = @At("RETURN"),
        remap = false,
        cancellable = true
    )
    private void limitCraftAttempts(int maxTimesToCraft, CallbackInfoReturnable<Integer> cir) {
        if (cir.getReturnValue() > Configurables.retryLimit) {
            cir.setReturnValue(Configurables.retryLimit);
        }
    }

}
