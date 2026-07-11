package com.phasico.infinistack.mixins.adventurebackpack;

import com.darkona.adventurebackpack.inventory.ContainerBackpack;
import com.phasico.infinistack.helper.FixedCraftingContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

@Mixin(ContainerBackpack.class)
@Pseudo
public abstract class MixinContainerBackpack implements FixedCraftingContainer {
}
