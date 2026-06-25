package com.phasico.infinistack.mixins.storagedrawers;

import com.jaquadro.minecraft.storagedrawers.block.tile.TileEntitySlave;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntitySlave.class)
@Pseudo
public abstract class MixinTileEntitySlave {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
