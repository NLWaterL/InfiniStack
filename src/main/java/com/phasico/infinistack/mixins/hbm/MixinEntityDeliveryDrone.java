package com.phasico.infinistack.mixins.hbm;

import com.hbm.entity.item.EntityDeliveryDrone; // entity\item\EntityDeliveryDrone.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Pseudo;

@Pseudo
@Mixin(EntityDeliveryDrone.class)
public abstract class MixinEntityDeliveryDrone {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}