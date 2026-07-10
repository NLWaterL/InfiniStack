package com.phasico.infinistack.mixins;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.phasico.infinistack.helper.logic.ItemCountDisplay;
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
        return ItemCountDisplay.getStackSizeText(value);
    }

    @Redirect(
            method = "renderItemOverlayIntoGUI(Lnet/minecraft/client/gui/FontRenderer;Lnet/minecraft/client/renderer/texture/TextureManager;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/FontRenderer;drawStringWithShadow(Ljava/lang/String;III)I"
            )
    )
    private int redirectStackSizeDraw(FontRenderer font, String text, int x, int y, int color,
                                      FontRenderer fontRenderer, TextureManager textureManager, ItemStack stack, int xPosition, int yPosition, String altText) {
        float scale = ItemCountDisplay.getDisplayScale(text, font.getUnicodeFlag());
        if (scale == 1.0f) {
            return font.drawStringWithShadow(text, x, y, color);
        }
        float inverse = 1.0f / scale;
        int scaledX = (int) ((xPosition + 17 - font.getStringWidth(text) * scale) * inverse);
        int scaledY = (int) ((yPosition + 16 - 7.0f * scale) * inverse);
        GL11.glScaled(scale, scale, scale);
        int result = font.drawStringWithShadow(text, scaledX, scaledY, color);
        GL11.glScaled(inverse, inverse, inverse);
        return result;
    }
}
