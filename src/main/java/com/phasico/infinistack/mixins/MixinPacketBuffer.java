package com.phasico.infinistack.mixins;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Overwrite;

import java.io.IOException;

@Mixin(value = PacketBuffer.class, priority = 1001) //EndlessIDs also overwrites the methods, I had to overwrite the changes
public abstract class MixinPacketBuffer extends ByteBuf {
    
    @Shadow
    public abstract void writeNBTTagCompoundToBuffer(NBTTagCompound tag) throws IOException;

    @Overwrite
    public void writeItemStackToBuffer(ItemStack stack) throws IOException {
        if (stack == null) {
            this.writeInt(-1);
        } else {
            this.writeInt(Item.getIdFromItem(stack.getItem())); //To make the mod compatible with EndlessIDs
            this.writeInt(stack.stackSize);
            this.writeShort(stack.getItemDamage());
            NBTTagCompound nbttagcompound = null;

            if (stack.getItem().isDamageable() || stack.getItem().getShareTag()) {
                nbttagcompound = stack.stackTagCompound;
            }

            this.writeNBTTagCompoundToBuffer(nbttagcompound);
        }
    }

    @Shadow
    public abstract NBTTagCompound readNBTTagCompoundFromBuffer() throws IOException;

    @Overwrite
    public ItemStack readItemStackFromBuffer() throws IOException {

        ItemStack stack = null;
        int id = this.readInt(); //To make the mod compatible with EndlessIDs

        if (id >= 0) {
            int size = this.readInt();
            short dmg = this.readShort();
            stack = new ItemStack(Item.getItemById(id), size, dmg);
            stack.stackTagCompound = this.readNBTTagCompoundFromBuffer();
        }
        return stack;
    }

}
