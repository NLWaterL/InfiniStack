package com.phasico.infinistack.mixins;

import net.minecraft.client.renderer.entity.RenderItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.phasico.infinistack.helper.logic.ItemCountDisplay;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RenderItem.class)
public abstract class MixinRenderItem {

    //Client Only
    @Redirect(
            method = "renderItemOverlayIntoGUI(Lnet/minecraft/client/gui/FontRenderer;Lnet/minecraft/client/renderer/texture/TextureManager;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/String;valueOf(I)Ljava/lang/String;"
            )
    )
    private String redirectStackSizeToFormatted(int value) {
    	if(Configurables.useAlternateDisplay){
            return ItemCountDisplay.formatStackSizeStyle2(value);
        } else {
        	return ItemCountDisplay.formatStackSize(value);
    	}
    }
}
