package com.phasico.infinistack.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.item.Item;
import com.phasico.infinistack.helper.Configurables;

@Mixin(Item.class)
public abstract class MixinItem {

    @Shadow
    protected int maxStackSize;


    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        maxStackSize = Configurables.maxStackSize;
    }

    /*@Overwrite
    public Item setMaxStackSize(int size) {
        if (size != 1) {
            this.maxStackSize = Configurables.maxStackSize;
        } else {
            this.maxStackSize = 1;
        }
        return (Item)(Object)this;
    }*/

    @Deprecated
    @Overwrite
    public int getItemStackLimit() {
        return this.maxStackSize == 1 ? 1 : Configurables.maxStackSize;
    }
}