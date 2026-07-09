package com.phasico.infinistack.mixins.adventurebackpack;

import com.darkona.adventurebackpack.inventory.ContainerAdventure;
import com.phasico.infinistack.helper.FixedCraftingContainer;
import org.spongepowered.asm.mixin.Mixin;

// GTNH version. Marker-only: the grid (InventoryCraftingBackpack) uses the plain vanilla
// InventoryCrafting mutation path, so MixinInventoryCrafting's suppression covers it; the result
// slot (SlotCraftResult extends SlotCrafting) calls super, composing with MixinSlotCrafting, and
// its func_75130_a CraftingManager lookup is memoized by MixinCraftingManager. Marking the
// ContainerAdventure base is inert for the crafting-less subclasses (copter, jetpack).
@Mixin(ContainerAdventure.class)
public abstract class MixinContainerAdventure implements FixedCraftingContainer {
}
