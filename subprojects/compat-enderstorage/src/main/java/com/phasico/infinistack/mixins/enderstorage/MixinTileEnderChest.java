package com.phasico.infinistack.mixins.enderstorage;

import codechicken.enderstorage.storage.item.TileEnderChest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEnderChest.class)
@Pseudo
public abstract class MixinTileEnderChest {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
