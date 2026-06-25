package com.phasico.infinistack.mixins.thaumcraft;

import thaumcraft.common.tiles.TileAlchemyFurnace;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileAlchemyFurnace.class)
@Pseudo
public abstract class MixinTileAlchemyFurnace {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
