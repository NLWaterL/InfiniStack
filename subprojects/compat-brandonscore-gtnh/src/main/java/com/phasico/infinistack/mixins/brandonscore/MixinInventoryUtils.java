package com.phasico.infinistack.mixins.brandonscore;

import com.brandon3055.brandonscore.common.utills.InventoryUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryUtils.class)
@Pseudo
public abstract class MixinInventoryUtils {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
