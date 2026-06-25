package com.phasico.infinistack.mixins.thaumcraft;

import thaumcraft.common.container.InventoryFake;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryFake.class)
@Pseudo
public abstract class MixinInventoryFake {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
