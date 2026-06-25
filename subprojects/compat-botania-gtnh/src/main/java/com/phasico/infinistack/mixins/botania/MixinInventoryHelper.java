package com.phasico.infinistack.mixins.botania;

import vazkii.botania.common.core.helper.InventoryHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryHelper.class)
@Pseudo
public abstract class MixinInventoryHelper {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
