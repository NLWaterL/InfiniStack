package com.phasico.infinistack.mixins.draconic;

import com.brandon3055.draconicevolution.common.container.ContainerDraconiumChest;
import com.phasico.infinistack.helper.FixedCraftingContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

@Mixin(ContainerDraconiumChest.class)
@Pseudo
public abstract class MixinContainerDraconiumChest implements FixedCraftingContainer {
}
