package com.phasico.infinistack.mixins.serverutilities;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import serverutils.lib.item.ItemStackSerializer;

@Mixin(ItemStackSerializer.class)
@Pseudo
public abstract class MixinItemStackSerializer {

    @Overwrite(remap = false)
    public static ItemStack parseItemWithName(NBTTagCompound nbt) {
        String id = nbt.getString("id");
        short dmg = nbt.getShort("Damage");
        int count = nbt.hasKey("Count", 3)
                ? nbt.getInteger("Count")
                : (nbt.hasKey("Count") ? nbt.getByte("Count") : 1);
        String tag = "";
        if (nbt.hasKey("tag")) {
            tag = nbt.getTag("tag").toString();
        }
        ItemStack stack = GameRegistry.makeItemStack(id, dmg, count, tag);
        if (stack != null) {
            stack.stackSize = count;
        }
        return stack;
    }

}
