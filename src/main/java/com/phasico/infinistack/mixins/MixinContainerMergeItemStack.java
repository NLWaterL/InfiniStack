package com.phasico.infinistack.mixins;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(value = Container.class, priority = 1001)
public abstract class MixinContainerMergeItemStack {

    @Shadow
    public List inventorySlots;

    //Replace bugtorch's mergeItemStack mixin to prevent item duplication
    @Overwrite
    public boolean mergeItemStack(ItemStack input, int start, int end, boolean reverse) {
        boolean merged = false;
        int increment = reverse ? -1 : 1;

        if (input.isStackable()) {
            int index = reverse ? end - 1 : start;
            while (input.stackSize > 0 && (reverse ? index >= start : index < end)) {
                Slot slot = (Slot) this.inventorySlots.get(index);
                ItemStack slotStack = slot.getStack();
                if (slotStack != null
                        && slotStack.getItem() == input.getItem()
                        && input.getItemDamage() == slotStack.getItemDamage()
                        && ItemStack.areItemStackTagsEqual(input, slotStack)) {
                    int limit = Math.min(input.getMaxStackSize(), slot.getSlotStackLimit());
                    int move = Math.min(limit - slotStack.stackSize, input.stackSize);
                    if (move > 0) {
                        ItemStack probe = input.copy();
                        probe.stackSize = move;
                        if (slot.isItemValid(probe)) {
                            slotStack.stackSize += move;
                            input.stackSize -= move;
                            slot.onSlotChanged();
                            merged = true;
                        }
                    }
                }
                index += increment;
            }
        }

        if (input.stackSize > 0) {
            int index = reverse ? end - 1 : start;
            while (input.stackSize > 0 && (reverse ? index >= start : index < end)) {
                Slot slot = (Slot) this.inventorySlots.get(index);
                if (slot.getStack() == null) {
                    int move = Math.min(slot.getSlotStackLimit(), input.stackSize);
                    ItemStack placed = input.copy();
                    placed.stackSize = move;
                    if (slot.isItemValid(placed)) {
                        slot.putStack(placed);
                        slot.onSlotChanged();
                        input.stackSize -= move;
                        merged = true;
                    }
                }
                index += increment;
            }
        }

        return merged;
    }
}
