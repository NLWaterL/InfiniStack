package com.phasico.infinistack.mixins.galaxyspace;

import galaxyspace.core.tile.machine.TileEntityAssemblyMachine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityAssemblyMachine.class)
@Pseudo
public abstract class MixinTileEntityAssemblyMachine {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
