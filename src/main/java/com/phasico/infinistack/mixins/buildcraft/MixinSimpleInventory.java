package com.phasico.infinistack.mixins.buildcraft;

import buildcraft.core.lib.inventory.SimpleInventory;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SimpleInventory.class)
public abstract class MixinSimpleInventory {

    @Shadow(remap = false)
    @Final
    private int stackLimit;

    @Overwrite(remap = false)
    public int func_70297_j_() {
        if(this.stackLimit > 1){
            return Configurables.maxStackSize;
        } else {
            return this.stackLimit;
        }
    }

}
