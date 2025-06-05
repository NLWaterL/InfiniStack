package com.phasico.infinistack.mixins.compactstorage;

import com.tattyseal.compactstorage.inventory.InventoryBackpack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryBackpack.class)
public abstract class MixinInventoryBackpack {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
