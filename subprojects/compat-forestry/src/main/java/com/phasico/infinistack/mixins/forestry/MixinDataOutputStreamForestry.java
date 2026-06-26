package com.phasico.infinistack.mixins.forestry;

import forestry.core.network.DataOutputStreamForestry;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(DataOutputStreamForestry.class)
@Pseudo
public abstract class MixinDataOutputStreamForestry {

    @Shadow(remap = false)
    public abstract void writeVarInt(int i) throws IOException;

    @Inject(
            method = "writeItemStack",
            at = @At("RETURN"),
            remap = false
    )
    private void writeIntegerSize(ItemStack itemstack, CallbackInfo ci) throws IOException {
        if (itemstack != null) {
            this.writeVarInt(itemstack.stackSize);
        }
    }

}
