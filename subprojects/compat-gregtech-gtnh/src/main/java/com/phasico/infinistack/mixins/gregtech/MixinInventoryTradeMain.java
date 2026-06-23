package com.phasico.infinistack.mixins.gregtech;

import gtPlusPlus.core.inventories.tradetable.InventoryTradeMain; // gtPlusPlus\core\inventories\tradetable\InventoryTradeMain.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryTradeMain.class)
@Pseudo
public abstract class MixinInventoryTradeMain {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}