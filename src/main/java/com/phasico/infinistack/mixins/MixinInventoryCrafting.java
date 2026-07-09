package com.phasico.infinistack.mixins;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import com.phasico.infinistack.helper.Configurables;
import com.phasico.infinistack.helper.SuppressibleCraftMatrix;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InventoryCrafting.class)
public abstract class MixinInventoryCrafting implements SuppressibleCraftMatrix {

    @Shadow
    private Container eventHandler;

    @Unique
    private boolean suppressMatrixEvents;

    @Overwrite
    public int getInventoryStackLimit()
    {
        return Configurables.maxStackSize;
    }

    @Override
    public Container getEventHandler() {
        return eventHandler;
    }

    @Override
    public void setSuppressMatrixEvents(boolean suppress) {
        this.suppressMatrixEvents = suppress;
    }

    @Override
    public boolean isMatrixEventsSuppressed() {
        return suppressMatrixEvents;
    }

    // decrStackSize and setInventorySlotContents each hard-code a call to
    // eventHandler.onCraftMatrixChanged(this). MixinSlotCrafting raises the suppress flag for the
    // duration of one craft (onPickupFromSlot) and fires the event itself exactly once afterwards,
    // so the per-mutation fires here would be redundant recipe searches.
    @Redirect(
        method = {"decrStackSize", "setInventorySlotContents"},
        at = @At(value = "INVOKE",
                 target = "Lnet/minecraft/inventory/Container;onCraftMatrixChanged(Lnet/minecraft/inventory/IInventory;)V")
    )
    private void suppressableCraftMatrixChanged(Container handler, IInventory matrix) {
        if (!suppressMatrixEvents) {
            handler.onCraftMatrixChanged(matrix);
        }
    }
}
