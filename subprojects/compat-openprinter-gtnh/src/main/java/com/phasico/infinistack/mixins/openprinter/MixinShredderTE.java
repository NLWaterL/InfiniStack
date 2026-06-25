package com.phasico.infinistack.mixins.openprinter;

import pcl.openprinter.tileentity.ShredderTE;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(ShredderTE.class)
@Pseudo
public abstract class MixinShredderTE {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
