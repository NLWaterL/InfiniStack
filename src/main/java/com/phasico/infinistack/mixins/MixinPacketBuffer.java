package com.phasico.infinistack.mixins;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;


@Mixin(value = PacketBuffer.class, priority = 1001) //Modular UI does the same thing
public abstract class MixinPacketBuffer extends ByteBuf {
    
    @Shadow
    public abstract void writeVarIntToBuffer(int p_150787_1_);

    @Shadow
    public abstract int readVarIntFromBuffer();

    @Inject(
            method = "writeItemStackToBuffer",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/network/PacketBuffer;writeNBTTagCompoundToBuffer(Lnet/minecraft/nbt/NBTTagCompound;)V",
                    shift = At.Shift.AFTER))
    public void writeIntegerSize(ItemStack stack, CallbackInfo ci) {
        this.writeVarIntToBuffer(stack.stackSize);
        }

    @Inject(
            method = "readItemStackFromBuffer",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/network/PacketBuffer;readNBTTagCompoundFromBuffer()Lnet/minecraft/nbt/NBTTagCompound;",
                    shift = At.Shift.AFTER),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    public void readIntegerSize(CallbackInfoReturnable<ItemStack> cir, ItemStack stack){
        stack.stackSize = this.readVarIntFromBuffer();
    }

}
