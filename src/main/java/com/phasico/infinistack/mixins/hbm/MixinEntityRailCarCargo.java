package com.phasico.infinistack.mixins.hbm;

import com.hbm.entity.train.EntityRailCarCargo; // hbm\entity\train\EntityRailCarCargo.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(EntityRailCarCargo.class)
@Pseudo
public abstract class MixinEntityRailCarCargo {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}