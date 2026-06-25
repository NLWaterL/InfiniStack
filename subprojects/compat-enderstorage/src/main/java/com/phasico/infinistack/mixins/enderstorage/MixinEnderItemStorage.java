package com.phasico.infinistack.mixins.enderstorage;

import codechicken.enderstorage.storage.item.EnderItemStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(EnderItemStorage.class)
@Pseudo
public abstract class MixinEnderItemStorage {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
