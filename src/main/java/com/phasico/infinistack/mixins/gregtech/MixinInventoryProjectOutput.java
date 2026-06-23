package com.phasico.infinistack.mixins.gregtech;

import gtPlusPlus.core.inventories.projecttable.InventoryProjectOutput; // gtPlusPlus\core\inventories\projecttable\InventoryProjectOutput.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryProjectOutput.class)
@Pseudo
public abstract class MixinInventoryProjectOutput {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}