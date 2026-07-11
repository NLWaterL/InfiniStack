package com.phasico.infinistack.mixins.craftingtweaks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.blay09.mods.craftingtweaks.DefaultProviderImpl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

@Mixin(DefaultProviderImpl.class)
@Pseudo
public abstract class MixinDefaultProviderImpl {

    @Overwrite(remap = false)
    public void balanceGrid(EntityPlayer entityPlayer, Container container, IInventory craftMatrix, int start, int size) {
        Map<String, List<ItemStack>> itemMap = new LinkedHashMap<>();
        Map<String, Long> itemCount = new HashMap<>();
        for (int i = start; i < start + size; ++i) {
            ItemStack itemStack = craftMatrix.getStackInSlot(i);
            if (itemStack == null || itemStack.getMaxStackSize() <= 1) continue;
            String key = itemStack.getUnlocalizedName() + "@" + itemStack.getItemDamage();
            List<ItemStack> list = itemMap.get(key);
            if (list == null) {
                list = new ArrayList<>();
                itemMap.put(key, list);
            }
            list.add(itemStack);
            Long count = itemCount.get(key);
            itemCount.put(key, (count == null ? 0L : count) + itemStack.stackSize);
        }
        for (Map.Entry<String, List<ItemStack>> entry : itemMap.entrySet()) {
            List<ItemStack> balanceList = entry.getValue();
            long totalCount = itemCount.get(entry.getKey());
            int countPerStack = (int) (totalCount / balanceList.size());
            int restCount = (int) (totalCount % balanceList.size());
            for (ItemStack itemStack : balanceList) {
                itemStack.stackSize = countPerStack;
            }
            int idx = 0;
            while (restCount > 0) {
                ItemStack itemStack = balanceList.get(idx);
                if (itemStack.stackSize < itemStack.getMaxStackSize()) {
                    ++itemStack.stackSize;
                    --restCount;
                }
                if (++idx >= balanceList.size()) {
                    idx = 0;
                }
            }
        }
        container.detectAndSendChanges();
    }
}
