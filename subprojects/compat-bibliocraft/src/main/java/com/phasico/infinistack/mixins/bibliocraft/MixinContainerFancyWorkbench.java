package com.phasico.infinistack.mixins.bibliocraft;

import com.phasico.infinistack.helper.FixedCraftingContainer;
import jds.bibliocraft.blocks.ContainerFancyWorkbench;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

// Marker-only: vanilla-shaped grid (plain InventoryCrafting + SlotCrafting). Its func_75130_a
// additionally pushes the grid to the tile (setPlayerGrid) - an idempotent state sync, so
// MixinSlotCrafting's one fire with the final matrix state is equivalent to the per-mutation
// fires it replaces; MixinCraftingManager memoizes the recipe search.
@Mixin(ContainerFancyWorkbench.class)
@Pseudo
public abstract class MixinContainerFancyWorkbench implements FixedCraftingContainer {
}
