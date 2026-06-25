package com.phasico.infinistack.mixins.codechickencore;

import codechicken.lib.inventory.InventoryCopy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryCopy.class)
@Pseudo
public abstract class MixinInventoryCopy {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
