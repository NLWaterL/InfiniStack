package com.phasico.infinistack.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(targets = "net.minecraft.inventory.ContainerBrewingStand$Ingredient")
public abstract class MixinContainerBrewingStandIngredient {

    @Overwrite
    public int getSlotStackLimit()
    {
        return Configurables.maxStackSize;
    }

}
