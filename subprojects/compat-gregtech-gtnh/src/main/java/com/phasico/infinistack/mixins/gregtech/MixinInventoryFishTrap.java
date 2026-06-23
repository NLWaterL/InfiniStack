package com.phasico.infinistack.mixins.gregtech;

import gtPlusPlus.core.inventories.InventoryFishTrap; // gtPlusPlus\core\inventories\InventoryFishTrap.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryFishTrap.class)
@Pseudo
public abstract class MixinInventoryFishTrap {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}