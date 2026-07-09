package com.phasico.infinistack.mixins.manametal;

import com.phasico.infinistack.helper.FixedCraftingContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import project.studio.manametalmod.fashion.ContainerWorkbenchClone;

// Marker-only: exact clone of the vanilla workbench (plain InventoryCrafting + SlotCrafting;
// func_75130_a is one CraftingManager lookup), fully covered by MixinSlotCrafting's batching and
// MixinCraftingManager's memoized recipe search.
@Mixin(ContainerWorkbenchClone.class)
@Pseudo
public abstract class MixinContainerWorkbenchClone implements FixedCraftingContainer {
}
