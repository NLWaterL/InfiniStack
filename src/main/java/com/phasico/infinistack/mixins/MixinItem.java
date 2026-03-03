package com.phasico.infinistack.mixins;

import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.item.Item;
import com.phasico.infinistack.helper.Configurables;

@Mixin(Item.class)
@Pseudo
public abstract class MixinItem {

    @Shadow
    protected int maxStackSize;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        if (this.maxStackSize != 1 || Configurables.allStackable) {
        maxStackSize = Configurables.maxStackSize;
    }
    }

    @Overwrite
    public Item setMaxStackSize(int size) {
        if (size == 1 && !Configurables.allStackable) {
            this.maxStackSize = 1;
        } else {
            this.maxStackSize = Configurables.maxStackSize;
        }
        return (Item)(Object)this;
    }

    @Deprecated
    @Overwrite
    public int getItemStackLimit() {
        return this.maxStackSize == 1 && !Configurables.allStackable ? 1 : Configurables.maxStackSize;
    }

}