package com.phasico.infinistack.mixins.adventurebackpack;

import com.darkona.adventurebackpack.inventory.ContainerBackpack;
import com.phasico.infinistack.helper.FixedCraftingContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

// Original (0.8c) version. Marker-only: vanilla-shaped grid (plain InventoryCrafting +
// SlotCrafting), fully covered by MixinSlotCrafting's batching and MixinCraftingManager's
// memoized recipe search.
@Mixin(ContainerBackpack.class)
@Pseudo
public abstract class MixinContainerBackpack implements FixedCraftingContainer {
}
