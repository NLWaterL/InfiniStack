package com.phasico.infinistack.mixins.daoza;

import com.daozcraft.container.Conbag;
import com.phasico.infinistack.helper.FixedCraftingContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

@Mixin(Conbag.class)
@Pseudo
public abstract class MixinConbag implements FixedCraftingContainer {
}
