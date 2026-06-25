package com.phasico.infinistack.mixins;

import codechicken.lib.inventory.InventoryCopy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryCopy.class)
@Pseudo
public abstract class MixinCCLInventoryCopy {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
