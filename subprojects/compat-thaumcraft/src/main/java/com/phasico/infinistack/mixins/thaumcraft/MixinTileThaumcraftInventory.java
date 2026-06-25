package com.phasico.infinistack.mixins.thaumcraft;

import thaumcraft.common.tiles.TileThaumcraftInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileThaumcraftInventory.class)
@Pseudo
public abstract class MixinTileThaumcraftInventory {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
