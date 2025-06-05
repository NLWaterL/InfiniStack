package com.phasico.infinistack.mixins.ironchest;

import cpw.mods.ironchest.TileEntityIronChest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityIronChest.class)
public abstract class MixinTileEntityIronChest {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
