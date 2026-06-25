package com.phasico.infinistack.mixins;

import codechicken.lib.data.MCDataOutputWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(MCDataOutputWrapper.class)
@Pseudo
public abstract class MixinCCLMCDataOutputWrapper {

    @ModifyVariable(
            method = "writeItemStack(Lnet/minecraft/item/ItemStack;Z)Lcodechicken/lib/data/MCDataOutputWrapper;",
            at = @At("HEAD"),
            argsOnly = true,
            remap = false)
    public boolean writeIntegerSize(boolean original){
        return true;
    }

}
