package com.phasico.infinistack.mixins.forestry;

import forestry.core.network.DataInputStreamForestry;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;

@Mixin(DataInputStreamForestry.class)
@Pseudo
public abstract class MixinDataInputStreamForestry {

    @Shadow(remap = false)
    public abstract int readVarInt() throws IOException;

    @Inject(
            method = "readItemStack",
            at = @At("RETURN"),
            remap = false
    )
    private void readIntegerSize(CallbackInfoReturnable<ItemStack> cir) throws IOException {
        ItemStack stack = cir.getReturnValue();
        if (stack != null) {
            stack.stackSize = this.readVarInt();
        }
    }

}
