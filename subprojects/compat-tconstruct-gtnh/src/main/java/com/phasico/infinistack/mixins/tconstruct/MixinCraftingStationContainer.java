package com.phasico.infinistack.mixins.tconstruct;

import com.phasico.infinistack.helper.FixedCraftingContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import tconstruct.tools.inventory.CraftingStationContainer;

@Mixin(CraftingStationContainer.class)
@Pseudo
public abstract class MixinCraftingStationContainer implements FixedCraftingContainer {
}
