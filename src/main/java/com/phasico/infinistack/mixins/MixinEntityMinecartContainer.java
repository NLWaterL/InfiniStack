package com.phasico.infinistack.mixins;

import net.minecraft.entity.item.EntityMinecartContainer;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityMinecartContainer.class)
public abstract class MixinEntityMinecartContainer {

    @Overwrite
    public int getInventoryStackLimit()
    {
        return Configurables.maxStackSize;
    }
}
