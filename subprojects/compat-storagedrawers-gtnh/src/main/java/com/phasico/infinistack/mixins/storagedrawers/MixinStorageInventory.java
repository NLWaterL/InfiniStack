package com.phasico.infinistack.mixins.storagedrawers;

import com.jaquadro.minecraft.storagedrawers.inventory.StorageInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(StorageInventory.class)
@Pseudo
public abstract class MixinStorageInventory {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
