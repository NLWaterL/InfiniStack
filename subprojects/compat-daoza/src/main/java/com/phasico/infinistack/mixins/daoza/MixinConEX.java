package com.phasico.infinistack.mixins.daoza;

import com.daozcraft.container.ConEX;
import com.phasico.infinistack.helper.FixedCraftingContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

@Mixin(ConEX.class)
@Pseudo
public abstract class MixinConEX implements FixedCraftingContainer {
}
