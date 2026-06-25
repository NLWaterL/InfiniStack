package com.phasico.infinistack.mixins.enderio;

import crazypants.enderio.machine.vacuum.TileVacuumChest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileVacuumChest.class)
@Pseudo
public abstract class MixinTileVacuumChest {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
