package com.phasico.infinistack.mixins.bibliocraft;

import jds.bibliocraft.tileentities.TileEntityFancyWorkbench;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityFancyWorkbench.class)
@Pseudo
public abstract class MixinTileEntityFancyWorkbench {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
