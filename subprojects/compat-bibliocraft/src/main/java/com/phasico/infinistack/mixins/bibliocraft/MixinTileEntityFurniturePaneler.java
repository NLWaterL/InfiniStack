package com.phasico.infinistack.mixins.bibliocraft;

import jds.bibliocraft.tileentities.TileEntityFurniturePaneler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityFurniturePaneler.class)
@Pseudo
public abstract class MixinTileEntityFurniturePaneler {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
