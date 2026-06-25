package com.phasico.infinistack.mixins.twilightforest;

import twilightforest.inventory.InventoryTFGoblinUncrafting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryTFGoblinUncrafting.class)
@Pseudo
public abstract class MixinInventoryTFGoblinUncrafting {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
