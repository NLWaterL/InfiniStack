package com.phasico.infinistack.mixins.natura;

import com.phasico.infinistack.helper.FixedCraftingContainer;
import mods.natura.gui.WorkbenchContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

// Marker-only: vanilla-shaped workbench (plain InventoryCrafting + SlotCrafting; func_75130_a is
// one CraftingManager lookup), fully covered by MixinSlotCrafting's batching and
// MixinCraftingManager's memoized recipe search.
@Mixin(WorkbenchContainer.class)
@Pseudo
public abstract class MixinWorkbenchContainer implements FixedCraftingContainer {
}
