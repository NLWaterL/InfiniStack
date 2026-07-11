package com.phasico.infinistack.mixins.manametal;

import com.phasico.infinistack.helper.FixedCraftingContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import project.studio.manametalmod.fashion.ContainerWorkbenchClone;

@Mixin(ContainerWorkbenchClone.class)
@Pseudo
public abstract class MixinContainerWorkbenchClone implements FixedCraftingContainer {
}
