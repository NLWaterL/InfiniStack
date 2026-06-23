package com.phasico.infinistack.mixins.gregtech;

import gtPlusPlus.core.inventories.tradetable.InventoryTradeOutput; // gtPlusPlus\core\inventories\tradetable\InventoryTradeOutput.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryTradeOutput.class)
@Pseudo
public abstract class MixinInventoryTradeOutput {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}