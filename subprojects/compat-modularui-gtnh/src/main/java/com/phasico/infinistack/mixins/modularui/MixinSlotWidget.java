package com.phasico.infinistack.mixins.modularui;

import com.gtnewhorizons.modularui.api.NumberFormatMUI;
import com.gtnewhorizons.modularui.api.drawable.TextRenderer;
import com.gtnewhorizons.modularui.common.widget.SlotWidget;
import com.phasico.infinistack.helper.logic.ItemCountDisplay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SlotWidget.class)
@Pseudo
public abstract class MixinSlotWidget {

    @Redirect(
            method = "drawSlot(Lnet/minecraft/inventory/Slot;Z)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/gtnewhorizons/modularui/api/NumberFormatMUI;formatWithSuffix(JLjava/lang/StringBuffer;)Ljava/lang/StringBuffer;"
            ),
            remap = false
    )
    private StringBuffer forceInfiniStackCount(NumberFormatMUI numberFormat, long number, StringBuffer toAppendTo) {
        return toAppendTo.append(ItemCountDisplay.getStackSizeText((int) Math.min(number, Integer.MAX_VALUE)));
    }

    @Redirect(
            method = "drawSlot(Lnet/minecraft/inventory/Slot;Z)V",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/String;length()I"
            ),
            remap = false
    )
    private int measureWithoutColourCodes(String text) {
        if(!ItemCountDisplay.shouldResizeCountText(TextRenderer.getFontRenderer().getUnicodeFlag())){
            return 1;
        }
        return Math.max(ItemCountDisplay.getVisibleLength(text), 3);
    }
}
