package com.phasico.infinistack.mixins.forestry;

import com.phasico.infinistack.helper.Configurables;
import forestry.core.utils.ItemStackUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Constant;

@Mixin(ItemStackUtil.class)
public abstract class MixinItemStackUtil {

    @ModifyConstant(
            method = "mergeStacks",
            constant = @Constant(intValue = 64),
            remap = false
    )
    private static int modifyDefaultStackLimit(int original) {
        return Configurables.maxStackSize;
    }
}
