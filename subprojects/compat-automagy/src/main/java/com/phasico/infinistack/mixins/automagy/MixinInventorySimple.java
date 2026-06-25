package com.phasico.infinistack.mixins.automagy;

import tuhljin.automagy.lib.inventory.InventorySimple;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventorySimple.class)
@Pseudo
public abstract class MixinInventorySimple {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
