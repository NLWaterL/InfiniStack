package com.phasico.infinistack.mixins;

import com.phasico.infinistack.helper.Configurables;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemStack.class)
public abstract class MixinItemStackClient {

    //Client Only
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
