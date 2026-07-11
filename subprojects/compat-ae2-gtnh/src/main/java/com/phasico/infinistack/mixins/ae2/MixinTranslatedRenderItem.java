package com.phasico.infinistack.mixins.ae2;

import appeng.api.config.TerminalFontSize;
import appeng.client.render.StackSizeRenderer;
import appeng.client.render.TranslatedRenderItem;
import com.phasico.infinistack.helper.logic.ItemCountDisplay;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TranslatedRenderItem.class)
@Pseudo
public abstract class MixinTranslatedRenderItem {

    @Redirect(
            method = "renderItemOverlayIntoGUI(Lnet/minecraft/client/gui/FontRenderer;Lnet/minecraft/client/renderer/texture/TextureManager;Lnet/minecraft/item/ItemStack;IILjava/lang/String;Lappeng/api/config/TerminalFontSize;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lappeng/client/render/StackSizeRenderer;getToBeRenderedStackSize(JLappeng/api/config/TerminalFontSize;)Ljava/lang/String;"
            ),
            remap = false
    )
    private String forceInfiniStackCount(long originalSize, TerminalFontSize fontSize) {
        return ItemCountDisplay.getStackSizeText(originalSize);
    }

    @Redirect(
            method = "renderItemOverlayIntoGUI(Lnet/minecraft/client/gui/FontRenderer;Lnet/minecraft/client/renderer/texture/TextureManager;Lnet/minecraft/item/ItemStack;IILjava/lang/String;Lappeng/api/config/TerminalFontSize;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lappeng/client/render/StackSizeRenderer;drawStackSize(IILjava/lang/String;Lnet/minecraft/client/gui/FontRenderer;Lappeng/api/config/TerminalFontSize;)V"
            ),
            remap = false
    )
    private void redirectStackSizeDraw(int offsetX, int offsetY, String text, FontRenderer font, TerminalFontSize fontSize,
                                          FontRenderer font1, TextureManager texManager, ItemStack stack, int x, int y, String customText, TerminalFontSize fontSize1) {
        if (customText != null) {
            StackSizeRenderer.drawStackSize(offsetX, offsetY, text, font, fontSize);
            return;
        }
        float scale = ItemCountDisplay.getDisplayScale(text, font.getUnicodeFlag());
        if (scale == 1.0f) {
            font.drawStringWithShadow(text, offsetX + 17 - font.getStringWidth(text), offsetY + 9, 0xFFFFFF);
            return;
        }
        float inverse = 1.0f / scale;
        int scaledX = (int) ((offsetX + 17 - font.getStringWidth(text) * scale) * inverse);
        int scaledY = (int) ((offsetY + 16 - 7.0f * scale) * inverse);
        GL11.glScaled(scale, scale, scale);
        font.drawStringWithShadow(text, scaledX, scaledY, 0xFFFFFF);
        GL11.glScaled(inverse, inverse, inverse);
    }
}
