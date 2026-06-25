package com.phasico.infinistack.mixins.cofhcore;

import cofh.lib.gui.container.InventoryContainerItemWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryContainerItemWrapper.class)
@Pseudo
public abstract class MixinInventoryContainerItemWrapper {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
