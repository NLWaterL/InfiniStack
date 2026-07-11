package com.phasico.infinistack.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import com.phasico.infinistack.helper.Configurables;
import com.phasico.infinistack.helper.StackSizeOverrides;

@Mixin(Item.class)
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
        int override = StackSizeOverrides.get((Item)(Object)this);
        if (override > 0) return override;
        return this.maxStackSize == 1 && !Configurables.allStackable ? 1 : Configurables.maxStackSize;
    }

    @Overwrite(remap = false)
    public int getItemStackLimit(ItemStack stack) {
        int override = StackSizeOverrides.get(stack.getItem(), stack.getItemDamage());
        if (override > 0) return override;
        return ((Item)(Object)this).getItemStackLimit();
    }
}