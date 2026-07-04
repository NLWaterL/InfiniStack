package com.phasico.infinistack.mixins.backpack;

import com.phasico.infinistack.helper.Configurables;
import de.eydamos.backpack.inventory.AbstractInventoryBackpack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractInventoryBackpack.class)
@Pseudo
public abstract class MixinAbstractInventoryBackpack {

    @Shadow protected int stackLimit;

    @Inject(
            method = "<init>",
            at = @At("RETURN"),
            remap = false
    )
    private void replaceMaxStackSize(CallbackInfo ci){
        if (this.stackLimit == 64) {
            this.stackLimit = Configurables.maxStackSize;
        }
    }

    @Overwrite(remap = false)
    public int func_70297_j_() {
        if (this.stackLimit == 64) {
            return Configurables.maxStackSize;
        }
        return this.stackLimit;
    }

}
