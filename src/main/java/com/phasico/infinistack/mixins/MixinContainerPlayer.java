package com.phasico.infinistack.mixins;

import com.phasico.infinistack.helper.FixedCraftingContainer;
import net.minecraft.inventory.ContainerPlayer;
import org.spongepowered.asm.mixin.Mixin;

// Marker-only: opts the player 2x2 grid into the batching + memoization systems (see
// MixinContainerWorkbench for the full picture).
@Mixin(ContainerPlayer.class)
public abstract class MixinContainerPlayer implements FixedCraftingContainer {
}
