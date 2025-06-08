package com.phasico.infinistack.mixins;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.Overwrite;

import java.io.IOException;

@Mixin(PacketBuffer.class)
public abstract class MixinPacketBuffer extends ByteBuf {

    @Redirect(
            method = "writeItemStackToBuffer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/network/PacketBuffer;writeByte(I)Lio/netty/buffer/ByteBuf;"
            )
    )
    private ByteBuf redirectWriteStackSize(PacketBuffer instance, int value) {
        return instance.writeInt(value);
    }

    @Shadow
    public abstract NBTTagCompound readNBTTagCompoundFromBuffer() throws IOException;

    @Overwrite
    public ItemStack readItemStackFromBuffer() throws IOException {
        ItemStack var1 = null;
        short var2 = this.readShort();
        if (var2 >= 0) {
            int var3 = this.readInt();
            short var4 = this.readShort();
            var1 = new ItemStack(Item.getItemById(var2), var3, var4);
            var1.stackTagCompound = this.readNBTTagCompoundFromBuffer();
        }
        return var1;
    }

}
