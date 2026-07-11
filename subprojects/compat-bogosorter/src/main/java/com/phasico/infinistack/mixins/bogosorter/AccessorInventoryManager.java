package com.phasico.infinistack.mixins.bogosorter;

import com.cleanroommc.bogosorter.common.dropoff.InventoryManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(InventoryManager.class)
public interface AccessorInventoryManager {

    @Invoker(value = "isStacksEqual", remap = false)
    boolean invokeIsStacksEqual(ItemStack left, ItemStack right);

    @Invoker(value = "getMaxAllowedStackSize", remap = false)
    int invokeGetMaxAllowedStackSize(IInventory inventory, ItemStack stack);

}
