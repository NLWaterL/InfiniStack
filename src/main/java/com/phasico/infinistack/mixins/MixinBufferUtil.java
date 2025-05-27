package com.phasico.infinistack.mixins;

import com.hbm.util.BufferUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BufferUtil.class)
public abstract class MixinBufferUtil {

    @Overwrite(remap = false)
    public static void writeItemStack(ByteBuf buf, ItemStack item) {
        if (item == null)
            buf.writeShort(-1);
        else {
            buf.writeShort(Item.getIdFromItem(item.getItem()));
            buf.writeInt(item.stackSize);
            buf.writeShort(item.getItemDamage());
            NBTTagCompound nbtTagCompound = null;

            if (item.getItem().isDamageable() || item.getItem().getShareTag())
                nbtTagCompound = item.stackTagCompound;

            BufferUtil.writeNBT(buf, nbtTagCompound);
        }
    }

    @Overwrite(remap = false)
    public static ItemStack readItemStack(ByteBuf buf) {
        ItemStack item = null;
        short id = buf.readShort();

        if (id >= 0) {
            int quantity = buf.readInt();
            short meta = buf.readShort();
            item = new ItemStack(Item.getItemById(id), quantity, meta);
            item.stackTagCompound = BufferUtil.readNBT(buf);
        }
        return item;
    }

}
