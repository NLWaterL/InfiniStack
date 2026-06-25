package com.phasico.infinistack.mixins.thaumcraft;

import thaumcraft.common.container.InventoryHandMirror;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryHandMirror.class)
@Pseudo
public abstract class MixinInventoryHandMirror {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
