package com.phasico.infinistack.mixins.railcraft;

import com.phasico.infinistack.helper.FixedCraftingContainer;
import mods.railcraft.common.gui.containers.RailcraftContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

@Mixin(RailcraftContainer.class)
@Pseudo
public abstract class MixinRailcraftContainer implements FixedCraftingContainer {
}
