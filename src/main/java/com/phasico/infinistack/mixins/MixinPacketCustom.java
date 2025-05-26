package com.phasico.infinistack.mixins;

import codechicken.lib.data.MCDataInput;
import codechicken.lib.data.MCDataOutput;
import codechicken.lib.packet.PacketCustom;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

@Pseudo
@Mixin(PacketCustom.class)
public abstract class MixinPacketCustom implements MCDataInput, MCDataOutput {

    @Overwrite(remap = false)
    public PacketCustom writeItemStack(ItemStack stack, boolean large) {
        if (stack == null) {
            writeShort(-1);
        } else {
            writeShort(Item.getIdFromItem(stack.getItem()));
            writeInt(stack.stackSize);
            writeShort(stack.getItemDamage());
            writeNBTTagCompound(stack.stackTagCompound);
        }
        return (PacketCustom)(Object)this;
    }

    @Overwrite(remap = false)
    public ItemStack readItemStack(boolean large) {
        ItemStack item = null;
        short itemID = readShort();

        if (itemID >= 0) {
            int stackSize = readInt();
            short damage = readShort();
            item = new ItemStack(Item.getItemById(itemID), stackSize, damage);
            item.stackTagCompound = readNBTTagCompound();
        }
        return item;
    }

}
