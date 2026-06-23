package com.phasico.infinistack.mixins.gregtech;

import gtPlusPlus.xmod.gregtech.api.items.GTMetaItemBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Constant;
import com.phasico.infinistack.helper.Configurables;

@Mixin(GTMetaItemBase.class)
@Pseudo
public abstract class MixinGTMetaItemBase {

    @ModifyConstant(
            method = "getItemStackLimit",
            constant = @Constant(intValue = 64),
            remap = false
    )
    public int replaceItemStackLimit(int original) {
        return Configurables.maxStackSize;
    }

}
