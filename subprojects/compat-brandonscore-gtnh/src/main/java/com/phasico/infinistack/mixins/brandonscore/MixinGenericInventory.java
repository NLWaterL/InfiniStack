package com.phasico.infinistack.mixins.brandonscore;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(targets = "com.brandon3055.brandonscore.common.utills.InventoryUtils$GenericInventory")
@Pseudo
public abstract class MixinGenericInventory {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
