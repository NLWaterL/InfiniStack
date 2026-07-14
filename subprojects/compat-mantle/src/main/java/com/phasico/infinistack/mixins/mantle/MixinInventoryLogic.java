package com.phasico.infinistack.mixins.mantle;

import com.phasico.infinistack.helper.Configurables;
import mantle.blocks.abstracts.InventoryLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(InventoryLogic.class)
@Pseudo
public abstract class MixinInventoryLogic {

    @Shadow
    protected int stackSizeLimit;

    @ModifyConstant(method = "<init>(I)V", constant = @Constant(intValue = 64), remap = false)
    private static int modifyMaxStackSize(int original){
        return Configurables.maxStackSize;
    }

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return stackSizeLimit == 64 ? Configurables.maxStackSize : stackSizeLimit;
    }

}
