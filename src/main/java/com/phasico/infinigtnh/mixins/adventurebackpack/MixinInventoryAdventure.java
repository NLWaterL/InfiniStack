package com.phasico.infinigtnh.mixins.adventurebackpack;

import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(targets = "com.darkona.adventurebackpack.inventory.InventoryAdventure")
public abstract class MixinInventoryAdventure {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
