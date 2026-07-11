package com.phasico.infinistack.mixins.adventurebackpack;

import com.darkona.adventurebackpack.inventory.ContainerAdventure;
import com.phasico.infinistack.helper.FixedCraftingContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

@Mixin(ContainerAdventure.class)
@Pseudo
public abstract class MixinContainerAdventure implements FixedCraftingContainer {
}
