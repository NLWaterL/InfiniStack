package com.phasico.infinistack.mixins.tconstruct;

import com.phasico.infinistack.helper.FixedCraftingContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import tconstruct.tools.inventory.CraftingStationContainer;

// Marker-only. The result slot is SlotCraftingStation extends SlotCrafting whose func_82870_a
// calls super, so MixinSlotCrafting's wrapping applies; its tool-modify branch mutates the grid
// BEFORE the super call and stays unsuppressed. The grid (InventoryCraftingStation) fires the
// change event itself - silenced by MixinInventoryCraftingStation. func_75130_a
// (modifyItem-or-recipe recompute) is idempotent, so one fire with the final state is equivalent;
// its CraftingManager lookup is memoized by MixinCraftingManager.
@Mixin(CraftingStationContainer.class)
@Pseudo
public abstract class MixinCraftingStationContainer implements FixedCraftingContainer {
}
