package com.phasico.infinistack.mixins.bdlib;

import net.bdew.lib.network.ItemStackSerialize;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Mixin(ItemStackSerialize.class)
@Pseudo
public abstract class MixinItemStackSerialize {

    @Shadow(remap = false)
    public abstract ItemStack stack();

    @Inject(
            method = "writeObject",
            at = @At("RETURN"),
            remap = false
    )
    public void writeIntegerSize(ObjectOutputStream out, CallbackInfo ci) throws IOException {

        out.writeInt(stack().stackSize);

    }

    @Inject(
            method = "readObject",
            at = @At("RETURN"),
            remap = false
    )
    public void readIntegerSize(ObjectInputStream in, CallbackInfo ci) throws IOException {

        int stackSize = in.readInt();
        if(stack() != null){
            stack().stackSize = stackSize;
        }

    }

}
