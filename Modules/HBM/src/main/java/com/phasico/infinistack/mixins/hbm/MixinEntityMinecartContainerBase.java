package com.phasico.infinistack.mixins.hbm;

import com.hbm.entity.cart.EntityMinecartContainerBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(EntityMinecartContainerBase.class)
public abstract class MixinEntityMinecartContainerBase {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
