package com.phasico.infinistack.mixins.hbm;

import com.hbm.entity.item.EntityDeliveryDrone;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.phasico.infinistack.helper.Configurables;


@Mixin(EntityDeliveryDrone.class)
public abstract class MixinEntityDeliverDrone {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
