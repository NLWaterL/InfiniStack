package com.phasico.infinistack.mixins.daoza;

import com.daozcraft.container.ConEX;
import com.phasico.infinistack.helper.FixedCraftingContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

// Marker-only: vanilla-shaped grid; the result slot is an anonymous SlotCrafting whose
// func_82870_a calls super then save3X3AndMarkInvEX, so MixinSlotCrafting's wrapping composes
// with it (the save runs after the single batched fire).
@Mixin(ConEX.class)
@Pseudo
public abstract class MixinConEX implements FixedCraftingContainer {
}
