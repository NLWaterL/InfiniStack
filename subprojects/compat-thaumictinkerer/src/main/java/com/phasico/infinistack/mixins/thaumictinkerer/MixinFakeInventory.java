package com.phasico.infinistack.mixins.thaumictinkerer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(targets = "thaumic.tinkerer.common.core.helper.FakeContainerCrafting$FakeInventory")
@Pseudo
public abstract class MixinFakeInventory {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
