package com.phasico.infinistack.mixins.draconic;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(targets = "com.brandon3055.draconicevolution.common.container.ContainerDissEnchanter$SlotBook")
@Pseudo
public abstract class MixinSlotBook {

    @Overwrite(remap = false)
    public int func_75219_a() {
        return Configurables.maxStackSize;
    }

}
