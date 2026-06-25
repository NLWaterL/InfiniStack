package com.phasico.infinistack.mixins;

import codechicken.lib.inventory.InventoryNBT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryNBT.class)
@Pseudo
public abstract class MixinCCLInventoryNBT {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
