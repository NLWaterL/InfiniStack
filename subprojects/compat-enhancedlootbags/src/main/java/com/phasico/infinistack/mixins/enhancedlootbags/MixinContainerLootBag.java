package com.phasico.infinistack.mixins.enhancedlootbags;

import eu.usrv.enhancedlootbags.core.ContainerLootBag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(ContainerLootBag.class)
@Pseudo
public abstract class MixinContainerLootBag {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
