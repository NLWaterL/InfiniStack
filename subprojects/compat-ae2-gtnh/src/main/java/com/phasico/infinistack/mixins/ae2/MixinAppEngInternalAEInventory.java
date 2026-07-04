package com.phasico.infinistack.mixins.ae2;

import appeng.tile.inventory.AppEngInternalAEInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AppEngInternalAEInventory.class)
@Pseudo
public abstract class MixinAppEngInternalAEInventory {

    @Shadow(remap = false)
    private int maxStack;

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return maxStack > Configurables.maxStackSize ? Configurables.maxStackSize : maxStack;
    }

    @ModifyConstant(
            method = "<init>",
            constant = @Constant(intValue = 64),
            remap = false
    )
    private static int modifyMaxStackSize(int original){
        return Configurables.maxStackSize;
    }

}
