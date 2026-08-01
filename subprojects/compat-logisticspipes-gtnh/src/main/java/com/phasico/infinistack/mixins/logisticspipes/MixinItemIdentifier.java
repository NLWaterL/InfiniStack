package com.phasico.infinistack.mixins.logisticspipes;

import com.phasico.infinistack.helper.Configurables;
import logisticspipes.utils.item.ItemIdentifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ItemIdentifier.class)
@Pseudo
public abstract class MixinItemIdentifier {

    @ModifyConstant(
            method = "getMaxStackSize",
            constant = @Constant(intValue = 64),
            remap = false
    )
    private int raiseStackCap(int original) {
        return Configurables.maxStackSize;
    }

}
