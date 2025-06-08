package com.phasico.infinistack.mixins.forestry;

import cpw.mods.fml.common.registry.GameData;
import forestry.core.network.DataInputStreamForestry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import java.io.IOException;

@Mixin(DataInputStreamForestry.class)
public abstract class MixinDataInputStreamForestry {

    @Shadow(remap = false)
    public abstract NBTTagCompound readNBTTagCompound() throws IOException;

    @Shadow(remap = false)
    public abstract int readVarInt() throws IOException;

    @Overwrite(remap = false)
    public ItemStack readItemStack() throws IOException {
        ItemStack itemstack = null;
        String itemName = ((DataInputStreamForestry)(Object)this).readUTF();
        if (!itemName.isEmpty()) {
            Item item = (Item) GameData.getItemRegistry().getRaw(itemName);
            int stackSize = ((DataInputStreamForestry)(Object)this).readInt();
            int meta = this.readVarInt();
            itemstack = new ItemStack(item, stackSize, meta);
            if (item.isDamageable() || item.getShareTag()) {
                itemstack.stackTagCompound = this.readNBTTagCompound();
            }
        }

        return itemstack;
    }

}
