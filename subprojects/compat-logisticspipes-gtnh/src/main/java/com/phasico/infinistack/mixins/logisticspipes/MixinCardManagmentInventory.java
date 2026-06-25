package com.phasico.infinistack.mixins.logisticspipes;

import logisticspipes.utils.CardManagmentInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(CardManagmentInventory.class)
@Pseudo
public abstract class MixinCardManagmentInventory {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
