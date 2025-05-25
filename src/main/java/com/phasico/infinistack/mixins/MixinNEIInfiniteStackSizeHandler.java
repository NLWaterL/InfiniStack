package com.phasico.infinistack.mixins;

import codechicken.nei.InfiniteStackSizeHandler;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(InfiniteStackSizeHandler.class)
public abstract class MixinNEIInfiniteStackSizeHandler {

    @Inject(method = "isItemInfinite", at = @At("HEAD"), cancellable = true, remap = false)
    private void overrideIsItemInfinite(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        boolean isInfinite = stack.stackSize == -1 || stack.stackSize > Configurables.maxStackSize;
        cir.setReturnValue(isInfinite);
    }

    @Overwrite(remap = false)
    public void onPlaceInfinite(ItemStack heldItem)
    {
        heldItem.stackSize = Configurables.maxStackSize + 1;
    }

    @Redirect(method = "replenishInfiniteStack",
            at = @At(value = "FIELD",
                    target = "Lnet/minecraft/item/ItemStack;stackSize:I",
                    opcode = org.objectweb.asm.Opcodes.PUTFIELD), remap = false)
    public void redirectReplenishStackSize(ItemStack stack, int value) {
        stack.stackSize = Configurables.maxStackSize + 1;
        }
}
