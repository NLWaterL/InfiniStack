package com.phasico.infinistack.mixins.gregtech;

import gtPlusPlus.core.inventories.InventoryCircuitProgrammer; // gtPlusPlus\core\inventories\InventoryCircuitProgrammer.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryCircuitProgrammer.class)
@Pseudo
public abstract class MixinInventoryCircuitProgrammer {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}