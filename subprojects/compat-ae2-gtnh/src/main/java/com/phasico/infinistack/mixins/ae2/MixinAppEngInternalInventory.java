package com.phasico.infinistack.mixins.ae2;

import appeng.tile.inventory.AppEngInternalInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AppEngInternalInventory.class)
@Pseudo
public abstract class MixinAppEngInternalInventory {

    @Shadow(remap = false)
    private int maxStack;

    @Shadow(remap = false)
    private boolean ignoreStackLimit;

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return this.ignoreStackLimit ? this.maxStack : Math.min(this.maxStack, Configurables.maxStackSize);
    }

    @ModifyConstant(
            method = "<init>(Lappeng/tile/inventory/IAEAppEngInventory;I)V",
            constant = @Constant(intValue = 64),
            remap = false
    )
    private static int modifyMaxStackSize(int original){
        return Configurables.maxStackSize;
    }

}
