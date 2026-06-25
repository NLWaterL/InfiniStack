package com.phasico.infinistack.mixins.harvestcraft;

import com.pam.harvestcraft.TileEntityPamFishTrap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityPamFishTrap.class)
@Pseudo
public abstract class MixinTileEntityPamFishTrap {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
