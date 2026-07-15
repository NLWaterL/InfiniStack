package com.phasico.infinistack.mixins.thaumicenergistics;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thaumicenergistics.common.parts.PartArcaneCraftingTerminal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(PartArcaneCraftingTerminal.class)
@Pseudo
public abstract class MixinPartArcaneCraftingTerminal {

    @Inject(method = "func_70297_j_", at = @At("RETURN"), cancellable = true, require = 0, remap = false)
    private void injectGetInventoryStackLimit(CallbackInfoReturnable<Integer> cir){
        cir.setReturnValue(Configurables.maxStackSize);
    }

}
