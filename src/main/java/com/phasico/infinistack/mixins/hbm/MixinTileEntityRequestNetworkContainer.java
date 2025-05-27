package com.phasico.infinistack.mixins.hbm;

import com.hbm.tileentity.network.TileEntityRequestNetworkContainer;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

@Pseudo
@Mixin(TileEntityRequestNetworkContainer.class)
public abstract class MixinTileEntityRequestNetworkContainer {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
