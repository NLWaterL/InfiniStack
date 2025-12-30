package com.phasico.infinistack.mixins;

import com.phasico.infinistack.helper.Configurables;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class MixinItemStack {

    @Shadow
    public int stackSize;

    @Redirect(method = "writeToNBT", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NBTTagCompound;setByte(Ljava/lang/String;B)V"))
    private void redirectSetByte(NBTTagCompound compound, String key, byte value) {
        if ("Count".equals(key)) {
            compound.setInteger("Count", this.stackSize);
        } else {
            compound.setByte(key, value);
        }
    }

    @Inject(method = "readFromNBT", at = @At("RETURN"))
    public void onReadFromNBT(NBTTagCompound compound, CallbackInfo ci) {
        ((ItemStack)(Object)this).stackSize = compound.getInteger("Count");
    }

    @Overwrite
    public int getMaxStackSize() {
        return ((ItemStack)(Object)this).getItem().getItemStackLimit() == 1 ? 1 : Configurables.maxStackSize;
    }
}
