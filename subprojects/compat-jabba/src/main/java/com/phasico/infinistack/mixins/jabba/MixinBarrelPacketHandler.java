package com.phasico.infinistack.mixins.jabba;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import mcp.mobius.betterbarrels.network.BarrelPacketHandler;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BarrelPacketHandler.class)
@Pseudo
public abstract class MixinBarrelPacketHandler {

    @Inject(
            method = "writeItemStackToBuffer",
            at = @At("RETURN"),
            remap = false
    )
    public void writeIntegerSize(ByteBuf target, ItemStack stack, CallbackInfo ci) {
        if (stack != null) {
            ByteBufUtils.writeVarInt(target, stack.stackSize, 5);
        }
    }

    @Inject(
            method = "readItemStackFromBuffer",
            at = @At("RETURN"),
            remap = false
    )
    public void readIntegerSize(ByteBuf dat, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack stack = cir.getReturnValue();
        if (stack != null) {
            stack.stackSize = ByteBufUtils.readVarInt(dat, 5);
        }
    }

}
