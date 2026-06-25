package com.phasico.infinistack.mixins.enhancedlootbags;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(targets = "eu.usrv.enhancedlootbags.core.ContainerLootBag$FakeLootBagInventory")
@Pseudo
public abstract class MixinFakeLootBagInventory {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
