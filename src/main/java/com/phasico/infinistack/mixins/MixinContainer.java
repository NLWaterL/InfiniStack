package com.phasico.infinistack.mixins;

import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Set;

@Mixin(Container.class)
public abstract class MixinContainer {
    @Overwrite
    public static void func_94525_a(Set<Slot> slotSet, int dragMode, ItemStack stack, int extra) {
        if (dragMode == 0) {
            if (slotSet != null && !slotSet.isEmpty()) {
                int total = stack.stackSize;
                int size = slotSet.size();

                stack.stackSize = total / size;

                } else {
                stack.stackSize = 0;
            }
        } else if (dragMode == 1) {
            stack.stackSize = 1 + extra;
        }
    }
}
