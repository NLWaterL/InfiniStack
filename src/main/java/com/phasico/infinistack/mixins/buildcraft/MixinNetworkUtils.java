package com.phasico.infinistack.mixins.buildcraft;

import buildcraft.core.lib.utils.NetworkUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(NetworkUtils.class)
public abstract class MixinNetworkUtils {

    @Overwrite(remap = false)
    public static void writeStack(ByteBuf data, ItemStack stack) {
        if (stack == null || stack.getItem() == null || stack.stackSize < 0) {
            data.writeInt(-1);
        } else {
            data.writeInt(stack.stackSize);
            data.writeBoolean(stack.hasTagCompound());
            data.writeShort(Item.getIdFromItem((Item)stack.getItem()));
            data.writeShort(stack.getItemDamage());
            if (stack.hasTagCompound()) {
                NetworkUtils.writeNBT(data, stack.getTagCompound());
            }
        }
    }

    @Overwrite(remap = false)
    public static ItemStack readStack(ByteBuf data) {

        int stackSize = data.readInt();
        if (stackSize == -1) {
            return null;
        }

        boolean hasCompound = data.readBoolean();
        int itemId = data.readUnsignedShort();
        short itemDamage = data.readShort();
        ItemStack stack = new ItemStack(Item.getItemById((int)itemId), stackSize, (int)itemDamage);
        if (hasCompound) {
            stack.setTagCompound(NetworkUtils.readNBT(data));
        }
        return stack;
    }

}
