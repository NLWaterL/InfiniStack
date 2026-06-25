package com.phasico.infinistack.mixins.hee;

import chylex.hee.tileentity.TileEntityAbstractInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityAbstractInventory.class)
@Pseudo
public abstract class MixinTileEntityAbstractInventory {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
