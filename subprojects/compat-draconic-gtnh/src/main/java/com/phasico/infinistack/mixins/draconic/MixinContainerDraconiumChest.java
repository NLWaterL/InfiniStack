package com.phasico.infinistack.mixins.draconic;

import com.brandon3055.draconicevolution.common.container.ContainerDraconiumChest;
import com.phasico.infinistack.helper.FixedCraftingContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

// Marker-only: shift-clicks run Draconic's own per-unit func_82846_b. The grid
// (InventoryCraftingChest) fires the change event itself - silenced by MixinInventoryCraftingChest
// while MixinSlotCrafting batches a craft to one fire, and its func_75130_a goes through vanilla
// CraftingManager.findMatchingRecipe - memoized by MixinCraftingManager. The old instant-craft
// body (bulk result routing chest-then-player) is parked with InstantCraftingLogic.
@Mixin(ContainerDraconiumChest.class)
@Pseudo
public abstract class MixinContainerDraconiumChest implements FixedCraftingContainer {
}
