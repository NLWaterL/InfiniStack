package com.phasico.infinistack.mixins;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.IOException;

@Mixin(PacketBuffer.class)
public abstract class MixinPacketBuffer {

    static {
        System.out.println(">>> MixinPacketBuffer loaded!");
    }

    @Shadow
    public abstract void writeNBTTagCompoundToBuffer(NBTTagCompound compound) throws IOException;

    @Shadow
    public abstract NBTTagCompound readNBTTagCompoundFromBuffer() throws IOException;

    @Shadow public abstract int readInt();

    @Shadow public abstract ByteBuf writeInt(int p_writeInt_1_);

    @Shadow public abstract ByteBuf writeShort(int p_writeShort_1_);

    @Shadow public abstract short readShort();

    @Shadow public abstract byte readByte();

    @Shadow public abstract ByteBuf writeByte(int p_ByteBuf_1_);

    @Overwrite
    public void writeItemStackToBuffer(ItemStack p_150788_1_) throws IOException
    {
        System.out.println("Custom write buffer called");
        System.out.println("writeItem" + p_150788_1_);
        if (p_150788_1_ == null)
        {
            this.writeShort(-1);
        }
        else
        {
            this.writeShort(Item.getIdFromItem(p_150788_1_.getItem()));
            if (p_150788_1_.stackSize >= 0 && p_150788_1_.stackSize <= 127) {
                this.writeByte(p_150788_1_.stackSize);
            } else {
                this.writeByte(-8);  // Sentinel value meaning "big count"
                this.writeInt(p_150788_1_.stackSize);
            }this.writeShort(p_150788_1_.getItemDamage());
            NBTTagCompound var2 = null;

            if (p_150788_1_.getItem().isDamageable() || p_150788_1_.getItem().getShareTag())
            {
                var2 = p_150788_1_.stackTagCompound;
            }

            this.writeNBTTagCompoundToBuffer(var2);
        }
    }

    @Overwrite
    public ItemStack readItemStackFromBuffer() throws IOException
    {
        System.out.println("Custom read buffer called");
        ItemStack var1 = null;
        short var2 = this.readShort();

        if (var2 >= 0)
        {
            int count = this.readByte();
            if (count == -8) {
                count = this.readInt();
            } short var4 = this.readShort();
            var1 = new ItemStack(Item.getItemById(var2), count, var4);
            var1.stackTagCompound = this.readNBTTagCompoundFromBuffer();
        }

        return var1;
    }

}
