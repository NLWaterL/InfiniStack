package com.phasico.infinistack.mixins.ae2;

import appeng.tile.inventory.AppEngInternalAEInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import com.phasico.infinistack.helper.Configurables;

@Mixin(AppEngInternalAEInventory.class)
@Pseudo
public abstract class MixinAppEngInternalAEInventory {

    @Shadow(remap = false)
    private int maxStack;

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return maxStack > Configurables.maxStackSize ? Configurables.maxStackSize : maxStack;
    }

}
