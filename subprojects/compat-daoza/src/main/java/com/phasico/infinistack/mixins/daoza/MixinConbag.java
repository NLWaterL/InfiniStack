package com.phasico.infinistack.mixins.daoza;

import com.daozcraft.container.Conbag;
import com.phasico.infinistack.helper.FixedCraftingContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

// Marker-only: vanilla-shaped grid; the result slot is an anonymous SlotCrafting whose
// func_82870_a calls super then markDirty, so MixinSlotCrafting's wrapping composes with it.
// Note Conbag's own func_75130_a also runs RCPmethod.findAllMatchingRecipes (its recipe-conflict
// list) - that scan is not memoized, batching just cuts it to once per craft (accepted: daoza is
// never loaded alongside GTNH-scale recipe lists).
@Mixin(Conbag.class)
@Pseudo
public abstract class MixinConbag implements FixedCraftingContainer {
}
