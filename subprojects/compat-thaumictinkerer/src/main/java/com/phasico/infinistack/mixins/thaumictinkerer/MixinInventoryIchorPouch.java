package com.phasico.infinistack.mixins.thaumictinkerer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(targets = "thaumic.tinkerer.common.block.tile.container.kami.ContainerIchorPouch$InventoryIchorPouch")
@Pseudo
public abstract class MixinInventoryIchorPouch {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
