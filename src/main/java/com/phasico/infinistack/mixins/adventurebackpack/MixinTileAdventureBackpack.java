package com.phasico.infinistack.mixins.adventurebackpack;

import com.darkona.adventurebackpack.block.TileAdventureBackpack;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TileAdventureBackpack.class)
public abstract class MixinTileAdventureBackpack {

    @Overwrite(remap = false)
    public int func_70297_j_()
    {
        return Configurables.maxStackSize;
    }

}
