package com.phasico.infinistack.mixins;

import codechicken.nei.InfiniteStackSizeHandler;
import codechicken.nei.NEIServerUtils;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

import org.spongepowered.asm.mixin.Overwrite;

@Mixin(InfiniteStackSizeHandler.class)
@Pseudo
public abstract class MixinNEIInfiniteStackSizeHandler {

    @Overwrite(remap = false)
    public boolean isItemInfinite(ItemStack stack){
        return stack.stackSize == -1;
    }

    @Overwrite(remap = false)
    public void onPlaceInfinite(ItemStack heldItem)
    {
        heldItem.stackSize = -1;
    }

    @Overwrite(remap = false)
    public void replenishInfiniteStack(InventoryPlayer inv, int slotNo)
    {
        ItemStack stack = inv.getStackInSlot(slotNo);
        stack.stackSize = -1;

        for(int i = 0; i < inv.getSizeInventory(); i++)
        {
            if(i == slotNo)
                continue;

            if(NEIServerUtils.areStacksSameType(stack, inv.getStackInSlot(i)))
                inv.setInventorySlotContents(i, null);
        }
    }
}
