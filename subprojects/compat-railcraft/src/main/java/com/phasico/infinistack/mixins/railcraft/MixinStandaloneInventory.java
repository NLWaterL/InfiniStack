package com.phasico.infinistack.mixins.railcraft;

import mods.railcraft.common.util.inventory.StandaloneInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(StandaloneInventory.class)
@Pseudo
public abstract class MixinStandaloneInventory {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
