package com.phasico.infinistack.mixins;

import com.phasico.infinistack.helper.FixedCraftingContainer;
import net.minecraft.inventory.ContainerWorkbench;
import org.spongepowered.asm.mixin.Mixin;

// Marker-only: opts the workbench into the batching + memoization systems. Shift-click crafting
// runs the vanilla per-unit path driven by MixinContainer's retry loop; MixinSlotCrafting reduces
// each craft to one onCraftMatrixChanged fire and MixinCraftingManager keeps that fire's recipe
// search O(1). InstantCraftingLogic is intentionally not wired up here (parked).
@Mixin(ContainerWorkbench.class)
public abstract class MixinContainerWorkbench implements FixedCraftingContainer {
}
