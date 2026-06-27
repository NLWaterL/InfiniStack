package com.phasico.infinistack.mixins.draconic;

import com.brandon3055.draconicevolution.common.inventory.SlotOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(SlotOutput.class)
@Pseudo
public abstract class MixinSlotOutput {

    @Overwrite(remap = false)
    public int func_75219_a() {
        return Configurables.maxStackSize;
    }

}
