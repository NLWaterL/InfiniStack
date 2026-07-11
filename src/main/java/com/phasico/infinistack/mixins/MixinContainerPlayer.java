package com.phasico.infinistack.mixins;

import com.phasico.infinistack.helper.FixedCraftingContainer;
import net.minecraft.inventory.ContainerPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ContainerPlayer.class)
public abstract class MixinContainerPlayer implements FixedCraftingContainer {
}
