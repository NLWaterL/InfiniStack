package com.phasico.infinistack.mixins.bloodarsenal;

import com.arc.bloodarsenal.common.tileentity.TilePortableAltar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TilePortableAltar.class)
@Pseudo
public abstract class MixinTilePortableAltar {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
