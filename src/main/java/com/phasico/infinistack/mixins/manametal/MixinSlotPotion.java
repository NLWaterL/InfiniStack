package com.phasico.infinistack.mixins.manametal;

import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets = "project.studio.manametalmod.produce.brewing.SlotPotion")
public abstract class MixinSlotPotion {

    @Shadow(remap = false)
    int type;

    @Inject(
            method = "func_75219_a",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private void injectGetSlotStackLimit(CallbackInfoReturnable<Integer> cir){
        cir.setReturnValue(this.type == 0 ? 1 : Configurables.maxStackSize);
    }

}
