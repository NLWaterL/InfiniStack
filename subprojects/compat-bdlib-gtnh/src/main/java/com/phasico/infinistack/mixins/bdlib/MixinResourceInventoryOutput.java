package com.phasico.infinistack.mixins.bdlib;

import net.bdew.lib.resource.ResourceInventoryOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(ResourceInventoryOutput.class)
@Pseudo
public abstract class MixinResourceInventoryOutput {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
