package com.phasico.infinistack.mixins;

import com.phasico.infinistack.helper.FixedCraftingContainer;
import net.minecraft.inventory.ContainerWorkbench;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ContainerWorkbench.class)
public abstract class MixinContainerWorkbench implements FixedCraftingContainer {
}
