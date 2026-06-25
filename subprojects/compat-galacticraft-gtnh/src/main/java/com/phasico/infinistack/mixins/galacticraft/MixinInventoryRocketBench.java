package com.phasico.infinistack.mixins.galacticraft;

import micdoodle8.mods.galacticraft.core.inventory.InventoryRocketBench;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryRocketBench.class)
@Pseudo
public abstract class MixinInventoryRocketBench {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
