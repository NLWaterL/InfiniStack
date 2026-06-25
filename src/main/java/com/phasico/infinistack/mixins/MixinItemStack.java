package com.phasico.infinistack.mixins;

import com.phasico.infinistack.helper.Configurables;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class MixinItemStack {

    @Shadow public int stackSize;

    @Inject(method = "writeToNBT", at = @At("RETURN"))
    private void afterWrite(NBTTagCompound nbt, CallbackInfoReturnable<NBTTagCompound> cir) {
        nbt.setInteger("Count", this.stackSize);
    }

    @Inject(method = "readFromNBT", at = @At("RETURN"))
    private void afterRead(NBTTagCompound nbt, CallbackInfo ci) {
        if (nbt.hasKey("Count", 3)) { // 3 = int
            ((ItemStack)(Object)this).stackSize = nbt.getInteger("Count");
        } else {
            ((ItemStack)(Object)this).stackSize = nbt.getByte("Count");
        }
    }

    @Overwrite
    public int getMaxStackSize() {
        return ((ItemStack)(Object)this).getItem().getItemStackLimit() == 1 && !Configurables.allStackable ? 1 : Configurables.maxStackSize;
    }

}
