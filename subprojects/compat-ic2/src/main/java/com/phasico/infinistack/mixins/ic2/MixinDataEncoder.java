package com.phasico.infinistack.mixins.ic2;

import ic2.core.network.DataEncoder;
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

import static ic2.core.network.DataEncoder.writeVarInt;
import static ic2.core.network.DataEncoder.readVarInt;

@Mixin(DataEncoder.class)
@Pseudo
public abstract class MixinDataEncoder {

    @Inject(
            method = "encode(Ljava/io/DataOutputStream;Ljava/lang/Object;Z)V",
            at = @At("RETURN"),
            remap = false
    )
    private static void writeIntegerSize(DataOutputStream os, Object o, boolean withType, CallbackInfo ci) throws IOException {

        if(o != null){
            if (o instanceof ItemStack) {
                ItemStack stack = (ItemStack)o;
                writeVarInt(os, stack.stackSize);
            }
        }

    }

    @Inject(
            method = "decode(Ljava/io/DataInputStream;I)Ljava/lang/Object;",
            at = @At("RETURN"),
            remap = false
    )
    private static void readIntegerSize(DataInputStream is, int type, CallbackInfoReturnable<Object> cir) throws IOException{

        if (type == 12) {
            int stackSize = readVarInt(is);
            ItemStack stack = (ItemStack)(cir.getReturnValue());
            if (stack != null) {
                stack.stackSize = stackSize;
            }
        }

    }

}
