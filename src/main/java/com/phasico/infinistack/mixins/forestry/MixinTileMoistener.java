package com.phasico.infinistack.mixins.forestry;

import com.phasico.infinistack.helper.Configurables;
import forestry.api.fuels.FuelManager;
import forestry.api.fuels.MoistenerFuel;
import forestry.core.inventory.IInventoryAdapter;
import forestry.factory.inventory.InventoryMoistener;
import forestry.factory.tiles.TileMoistener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TileMoistener.class)
public abstract class MixinTileMoistener {


    /**
     * For the percent system to work correctly when stack size limit is not 64.
     * Use long instead of int.
     */
    @Overwrite(remap = false)
    public boolean hasFuelMin(float percentage) {
        long max = 0;
        long avail = 0;
        IInventoryAdapter inventory = ((TileMoistener)(Object)this).getInternalInventory();

        for (int i = InventoryMoistener.SLOT_STASH_1; i < InventoryMoistener.SLOT_RESERVOIR_1; i++) {
            if (inventory.getStackInSlot(i) == null) {
                max += Configurables.maxStackSize;
                continue;
            }
            if (FuelManager.moistenerResource.containsKey(inventory.getStackInSlot(i))) {
                MoistenerFuel res = FuelManager.moistenerResource.get(inventory.getStackInSlot(i));
                if (res.item.isItemEqual(inventory.getStackInSlot(i))) {
                    max += Configurables.maxStackSize;
                    avail += inventory.getStackInSlot(i).stackSize;
                }
            }
        }

        return (float)avail / (float)max > percentage;
    }
}
