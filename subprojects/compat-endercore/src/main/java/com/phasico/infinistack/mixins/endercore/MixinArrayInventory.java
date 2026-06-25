package com.phasico.infinistack.mixins.endercore;

import com.enderio.core.common.util.ArrayInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(ArrayInventory.class)
@Pseudo
public abstract class MixinArrayInventory {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
