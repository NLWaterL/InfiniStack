package com.phasico.infinistack.mixins;

import codechicken.lib.inventory.InventoryUtils;
import com.phasico.infinistack.helper.Configurables;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(InventoryUtils.class)
@Pseudo
public abstract class MixinCCLInventoryUtils {

    @ModifyConstant(
            method = "writeItemStacksToTag([Lnet/minecraft/item/ItemStack;)Lnet/minecraft/nbt/NBTTagList;",
            constant = @Constant(intValue = 64),
            remap = false
    )
    private static int expandItemStackLimit(int original) {
        return Configurables.maxStackSize;
    }

    @Overwrite(remap = false)
    public static ItemStack loadPersistant(NBTTagCompound tag) {
        String name = tag.getString("name");
        Item item = (Item) Item.itemRegistry.getObject(name);
        if(item == null) return null;

        int count = tag.hasKey("Count", 3)
                ? tag.getInteger("Count")
                : (tag.hasKey("Count") ? tag.getByte("Count") : 1);

        int damage = tag.hasKey("Damage")
                ? tag.getShort("Damage")
                : 0;

        ItemStack stack = new ItemStack(item, count, damage);
        if(tag.hasKey("tag", 10))
            stack.stackTagCompound = tag.getCompoundTag("tag");
        return stack;
    }

}
