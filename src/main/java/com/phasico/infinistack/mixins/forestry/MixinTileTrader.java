package com.phasico.infinistack.mixins.forestry;

import com.phasico.infinistack.helper.Configurables;
import forestry.core.utils.ItemStackUtil;
import forestry.mail.tiles.TileTrader;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TileTrader.class)
public abstract class MixinTileTrader {


    /**
     * For the percent system to work correctly when stack size limit is not 64.
     * Use long instead of int.
     */
    @Overwrite(remap = false)
    private float percentOccupied(int startSlot, int countSlots, ItemStack item) {
        long count = 0;
        long total = 0;

        IInventory tradeInventory = ((TileTrader)(Object)this).getInternalInventory();
        for (int i = startSlot; i < startSlot + countSlots; i++) {
            ItemStack itemInSlot = tradeInventory.getStackInSlot(i);
            if (itemInSlot == null) {
                total += Configurables.maxStackSize;
            } else {
                total += itemInSlot.getMaxStackSize();
                if (item == null || ItemStackUtil.isIdenticalItem(itemInSlot, item)) {
                    count += itemInSlot.stackSize;
                }
            }
        }

        return ((float) count / (float) total);
    }
}
