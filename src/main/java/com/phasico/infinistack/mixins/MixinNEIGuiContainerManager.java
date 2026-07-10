package com.phasico.infinistack.mixins;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.guihook.GuiContainerManager;
import codechicken.nei.util.ReadableNumberConverter;
import com.phasico.infinistack.helper.logic.ItemCountDisplay;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiContainerManager.class)
@Pseudo
public abstract class MixinNEIGuiContainerManager {

    //Client only

    //"inventory.dynamicFontSize" enabled: force our format in place of NEI's converter.
    @Redirect(
            method = "lambda$drawItem$0",
            at = @At(
                    value = "INVOKE",
                    target = "Lcodechicken/nei/util/ReadableNumberConverter;toWideReadableForm(J)Ljava/lang/String;"
            ),
            require = 0, remap = false
    )
    private static String forceInfiniStackCount(ReadableNumberConverter converter, long value) {
        return ItemCountDisplay.getStackSizeText(value);
    }

    //"inventory.dynamicFontSize" disabled: same, for the plain String.valueOf branch.
    @Redirect(
            method = "lambda$drawItem$0",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/String;valueOf(I)Ljava/lang/String;"
            ),
            require = 0, remap = false
    )
    private static String forceInfiniStackCountPlain(int value) {
        return ItemCountDisplay.getStackSizeText(value);
    }

    @Redirect(
            method = "lambda$drawItem$0",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/String;length()I"
            ),
            require = 0, remap = false
    )
    private static int measureWithoutColourCodes(String text) {
        if (!ItemCountDisplay.shouldResizeCountText(GuiDraw.fontRenderer.getUnicodeFlag())) {
            return 1;
        }
        return Math.max(ItemCountDisplay.getVisibleLength(text), 3);
    }
}
