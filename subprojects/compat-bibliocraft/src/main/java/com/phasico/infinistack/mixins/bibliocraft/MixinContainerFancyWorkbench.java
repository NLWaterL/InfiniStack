package com.phasico.infinistack.mixins.bibliocraft;

import com.phasico.infinistack.helper.FixedCraftingContainer;
import jds.bibliocraft.blocks.ContainerFancyWorkbench;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

@Mixin(ContainerFancyWorkbench.class)
@Pseudo
public abstract class MixinContainerFancyWorkbench implements FixedCraftingContainer {
}
