package com.phasico.infinistack.mixins.adventurebackpack;

import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

@Mixin(targets = "com.darkona.adventurebackpack.block.TileAdventure")
@Pseudo
public abstract class MixinTileAdventure {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }


}