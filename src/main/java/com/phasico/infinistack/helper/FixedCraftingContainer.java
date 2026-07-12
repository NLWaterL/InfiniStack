package com.phasico.infinistack.helper;

/**
 * Marker interface for containers whose crafting flow is safe to batch: MixinSlotCrafting silences
 * the crafting matrix's per-mutation onCraftMatrixChanged calls for the duration of one
 * onPickupFromSlot call and fires the event exactly once on return.
 *
 * Safe means the container's onCraftMatrixChanged is an idempotent recompute: firing it once
 * with the final matrix state must be equivalent to firing it after every intermediate mutation.
 * Most vanilla-style workbench qualifies.
 */
public interface FixedCraftingContainer {
}
