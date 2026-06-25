package com.phasico.infinistack.mixins;

import codechicken.lib.data.MCDataInput;
import codechicken.lib.data.MCDataOutput;
import codechicken.lib.packet.PacketCustom;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PacketCustom.class)
@Pseudo
public abstract class MixinCCLPacketCustom implements MCDataInput, MCDataOutput {

    @ModifyVariable(
            method = "writeItemStack(Lnet/minecraft/item/ItemStack;Z)Lcodechicken/lib/packet/PacketCustom;",
            at = @At("HEAD"),
            argsOnly = true,
            remap = false)
    public boolean writeIntegerSize(boolean original){
        return true;
    }

    @ModifyVariable(
            method = "readItemStack(Z)Lnet/minecraft/item/ItemStack;",
            at = @At("HEAD"),
            argsOnly = true,
            remap = false)
    public boolean readIntegerSize(boolean original){
        return true;
    }

}
