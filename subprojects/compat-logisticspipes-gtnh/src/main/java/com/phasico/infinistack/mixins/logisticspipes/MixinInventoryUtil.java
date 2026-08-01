package com.phasico.infinistack.mixins.logisticspipes;

import logisticspipes.utils.InventoryUtil;
import logisticspipes.utils.item.ItemIdentifier;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(InventoryUtil.class)
@Pseudo
public abstract class MixinInventoryUtil {

    @Shadow(remap = false)
    @Final
    protected IInventory _inventory;

   @Overwrite(remap = false)
    public int roomForItem(ItemIdentifier item, int count) {
        long totalRoom = 0;
        int stackLimit = this._inventory.getInventoryStackLimit();
        for (int i = 0; i < this._inventory.getSizeInventory() && count > totalRoom; ++i) {
            ItemStack stack = this._inventory.getStackInSlot(i);
            if (stack == null) {
                if (!this._inventory.isItemValidForSlot(i, item.unsafeMakeNormalStack(1))) continue;
                totalRoom += Math.min(stackLimit, item.getMaxStackSize());
                continue;
            }
            if (!ItemIdentifier.get(stack).equals(item)) continue;
            totalRoom += Math.max(0L, Math.min(stackLimit, item.getMaxStackSize()) - (long) stack.stackSize);
        }
        return (int) Math.min(totalRoom, Integer.MAX_VALUE);
    }

}
