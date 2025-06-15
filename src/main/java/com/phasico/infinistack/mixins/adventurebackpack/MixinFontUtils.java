package com.phasico.infinistack.mixins.adventurebackpack;

import codechicken.lib.render.FontUtils;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(FontUtils.class)
public abstract class MixinFontUtils {

    //Maybe later, I will add a config option to let it use my own display logic.
    //Right now, just let it calculate stack number based on maxStackSize.

    @ModifyConstant(
            method = "drawItemQuantity",
            constant = @Constant(intValue = 64),
            remap = false
    )
    private static int modifyMaxStackSize(int original){
        return Configurables.maxStackSize;
    }

}
