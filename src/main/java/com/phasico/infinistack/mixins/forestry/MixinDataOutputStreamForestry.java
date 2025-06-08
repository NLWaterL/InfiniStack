package com.phasico.infinistack.mixins.forestry;

import forestry.core.network.DataOutputStreamForestry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;

import java.io.IOException;

@Mixin(DataOutputStreamForestry.class)
public abstract class MixinDataOutputStreamForestry {

    @Redirect(method = "writeItemStack",
            at = @At(value = "INVOKE",
            target = "Lforestry/core/network/DataOutputStreamForestry;writeByte(I)V"),
            remap = false)
    private void writeIntInstead(DataOutputStreamForestry instance, int value) throws IOException {
        instance.writeInt(value);
    }

}
