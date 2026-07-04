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

    // CRAFT_SHIFT / CRAFT_STACK compute maxTimesToCraft = func_77976_d() / howManyPerCraft.
    // Redirect func_77976_d() to maxStackSize so the batch is sized for large stacks.
    @Redirect(
        method = "doClick",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;func_77976_d()I"),
        remap = false
    )
    private int redirectCraftClickMaxStack(ItemStack stack) {
        return Configurables.maxStackSize;
    }

    // capCraftingAttempts() is the intended extension point AE2 left for capping batch
    // crafts. Inject at RETURN to bound it by retryLimit so large maxStackSize values
    // don't cause a server stall.
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
