package com.phasico.infinistack.mixins.railcraft;

import com.phasico.infinistack.helper.FixedCraftingContainer;
import mods.railcraft.common.gui.containers.RailcraftContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

// Marker-only: targets the shared Railcraft base container; only ContainerCartWork (the work
// cart) wires a SlotCrafting + InventoryCrafting, so marking the whole hierarchy as
// FixedCraftingContainer is inert for every other Railcraft container. ContainerCartWork's
// func_75130_a is one CraftingManager lookup - memoized by MixinCraftingManager.
@Mixin(RailcraftContainer.class)
@Pseudo
public abstract class MixinRailcraftContainer implements FixedCraftingContainer {
}
