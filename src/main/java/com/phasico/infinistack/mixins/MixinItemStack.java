package com.phasico.infinistack.mixins;

import com.phasico.infinistack.helper.Configurables;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.phasico.infinistack.helper.Logger;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemStack.class)
public abstract class MixinItemStack {

    @Shadow
    public int stackSize;

    @Redirect(method = "writeToNBT", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NBTTagCompound;setByte(Ljava/lang/String;B)V"))
    private void redirectSetByte(NBTTagCompound compound, String key, byte value) {
        if ("Count".equals(key)) {
            compound.setInteger("Count", this.stackSize);
            Logger.debug("writeToNBT - Setting Count as integer: " + this.stackSize);
        } else {
            compound.setByte(key, value);
        }
    }

    @Inject(method = "readFromNBT", at = @At("RETURN"))
    public void onReadFromNBT(NBTTagCompound compound, CallbackInfo ci) {

        Logger.debug("readFromNBT called for item ID: " + compound.getShort("id") + ", Count: " + compound.getInteger("Count"));
        ((ItemStack)(Object)this).stackSize = compound.getInteger("Count");

    }

    @Inject(method = "getTooltip", at = @At("RETURN"))
    public void displayItemCount(EntityPlayer p_82840_1_, boolean p_82840_2_, CallbackInfoReturnable<List> cir){
        List tooltip = cir.getReturnValue();

        int size = ((ItemStack)(Object)this).stackSize;

        if (tooltip != null && size > 999 &&
                (GuiScreen.isShiftKeyDown()) || Configurables.alwaysShowCount) {

            if (size < 100000) {

                tooltip.add(1, EnumChatFormatting.DARK_AQUA + Integer.toString(((ItemStack) (Object) this).stackSize));

            } else {

                tooltip.add(1, EnumChatFormatting.DARK_AQUA + String.format("%,d", ((ItemStack) (Object) this).stackSize));
            }
        }
    }
}
