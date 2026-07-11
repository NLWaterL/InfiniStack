package com.phasico.infinistack.mixins.morefurnaces;

import cubex2.mods.morefurnaces.tileentity.TileEntityIronFurnace; // cubex2\mods\morefurnaces\tileentity\TileEntityIronFurnace.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityIronFurnace.class)
@Pseudo
public abstract class MixinTileEntityIronFurnace {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
