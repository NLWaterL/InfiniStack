package com.phasico.infinistack.mixins.modularui2;

import com.cleanroommc.modularui.drawable.GuiDraw;
import com.cleanroommc.modularui.drawable.text.TextRenderer;
import com.cleanroommc.modularui.utils.NumberFormat;
import com.phasico.infinistack.helper.logic.ItemCountDisplay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiDraw.class)
@Pseudo
public abstract class MixinGuiDraw {

    @Redirect(
            method = "drawAmountText",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/cleanroommc/modularui/utils/NumberFormat$Params;format(D)Ljava/lang/String;"
            ),
            remap = false
    )
    private static String forceInfiniStackCount(NumberFormat.Params params, double amount) {
        return ItemCountDisplay.getStackSizeText((int) Math.min(amount, Integer.MAX_VALUE));
    }

    @Redirect(
            method = "drawAmountText",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/String;length()I"
            ),
            remap = false
    )
    private static int measureWithoutColourCodes(String text) {
        if(!ItemCountDisplay.shouldResizeCountText(TextRenderer.getFontRenderer().getUnicodeFlag())){
            return 1;
        }
        return Math.max(ItemCountDisplay.getVisibleLength(text), 3);
    }
}
