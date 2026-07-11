package com.phasico.infinistack.mixins.bogosorter;

import com.cleanroommc.bogosorter.common.dropoff.DropOffHandler;
import com.cleanroommc.bogosorter.common.dropoff.InventoryManager;
import com.cleanroommc.bogosorter.compat.Mods;
import com.jaquadro.minecraft.storagedrawers.block.tile.TileEntityDrawers;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DropOffHandler.class)
@Pseudo
public abstract class MixinDropOffHandler {

    @Shadow(remap = false)
    @Final
    private InventoryManager inventoryManager;

    @Shadow(remap = false)
    @Final
    private ItemStack[] playerStacks;

    @Shadow(remap = false)
    private int startSlot;

    @Shadow(remap = false)
    private int endSlot;

    @Overwrite(remap = false)
    private void movePlayerStack(int playerStackIndex, IInventory toInventory) {
        Integer emptySlotIndex = null;
        boolean hasSameStack = false;
        AccessorInventoryManager manager = (AccessorInventoryManager) (Object) this.inventoryManager;
        for (int i = this.startSlot; i < this.endSlot; ++i) {
            ItemStack toCurrentStack = toInventory.getStackInSlot(i);
            if (toCurrentStack == null) {
                if (emptySlotIndex == null) {
                    emptySlotIndex = i;
                }
                continue;
            }
            if (!manager.invokeIsStacksEqual(toCurrentStack, this.playerStacks[playerStackIndex])) {
                continue;
            }
            hasSameStack = true;
            int toCurrentStackMaxSize = manager.invokeGetMaxAllowedStackSize(toInventory, toCurrentStack);
            if (Mods.StorageDrawers.isLoaded() && toInventory instanceof TileEntityDrawers) {
                TileEntityDrawers drawer = (TileEntityDrawers) toInventory;
                int drawerSlot = drawer.getDrawerInventory().getDrawerSlot(i);
                if (drawer.canInsertItem(drawerSlot, this.playerStacks[playerStackIndex], 0)) {
                    int countAdded = drawer.putItemsIntoSlot(drawerSlot, this.playerStacks[playerStackIndex], this.playerStacks[playerStackIndex].stackSize);
                    if (countAdded == 0) {
                        continue;
                    }
                    if (this.playerStacks[playerStackIndex].stackSize <= 0) {
                        this.playerStacks[playerStackIndex] = null;
                    }
                }
                return;
            }
            if ((long) toCurrentStack.stackSize + (long) this.playerStacks[playerStackIndex].stackSize <= toCurrentStackMaxSize) {
                toCurrentStack.stackSize += this.playerStacks[playerStackIndex].stackSize;
                this.playerStacks[playerStackIndex] = null;
                return;
            }
            int leftToMax = toCurrentStackMaxSize - toCurrentStack.stackSize;
            toCurrentStack.stackSize = toCurrentStackMaxSize;
            this.playerStacks[playerStackIndex].stackSize -= leftToMax;
        }
        if (hasSameStack && emptySlotIndex != null && toInventory.isItemValidForSlot(emptySlotIndex, this.playerStacks[playerStackIndex])) {
            toInventory.setInventorySlotContents(emptySlotIndex, this.playerStacks[playerStackIndex]);
            this.playerStacks[playerStackIndex] = null;
        }
    }
}
