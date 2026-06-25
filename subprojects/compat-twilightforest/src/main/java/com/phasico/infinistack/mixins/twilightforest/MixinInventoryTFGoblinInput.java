package com.phasico.infinistack.mixins.twilightforest;

import twilightforest.inventory.InventoryTFGoblinInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryTFGoblinInput.class)
@Pseudo
public abstract class MixinInventoryTFGoblinInput {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
