package com.phasico.infinistack.helper;

/**
 * Marker interface for containers whose crafting flow is safe to batch: MixinSlotCrafting silences
 * the crafting matrix's per-mutation onCraftMatrixChanged fires for the duration of one
 * onPickupFromSlot call and fires the event exactly once on return (the AE2/Backpack shape).
 *
 * Safe means the container's onCraftMatrixChanged is an idempotent recompute (result slot, tile
 * sync, NBT save) - firing it once with the final matrix state must be equivalent to firing it
 * after every intermediate mutation. Every vanilla-style workbench qualifies. A container mixin
 * opts in by implementing this interface.
 */
public interface FixedCraftingContainer {
}
