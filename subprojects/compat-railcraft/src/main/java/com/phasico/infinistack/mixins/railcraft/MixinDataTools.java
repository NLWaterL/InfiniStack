package com.phasico.infinistack.mixins.railcraft;

import mods.railcraft.common.util.network.DataTools;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Mixin(DataTools.class)
@Pseudo
public abstract class MixinDataTools {

    @Inject(
            method = "writeItemStack",
            at = @At("RETURN"),
            remap = false)
    private static void writeIntegerSize(ItemStack stack, DataOutputStream dataStream, CallbackInfo ci) throws IOException {
        if (stack != null) {
            dataStream.writeInt(stack.stackSize);
        }
    }

    @Inject(
            method = "readItemStack",
            at = @At("RETURN"),
            remap = false)
    private static void readIntegerSize(DataInputStream dataStream, CallbackInfoReturnable<ItemStack> cir) throws IOException {
        ItemStack stack = cir.getReturnValue();
        if (stack != null) {
            stack.stackSize = dataStream.readInt();
        }
    }


}
