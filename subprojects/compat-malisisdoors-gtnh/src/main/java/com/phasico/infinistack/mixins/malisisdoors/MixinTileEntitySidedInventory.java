package com.phasico.infinistack.mixins.malisisdoors;

import net.malisis.core.tileentity.TileEntitySidedInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntitySidedInventory.class)
@Pseudo
public abstract class MixinTileEntitySidedInventory {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
