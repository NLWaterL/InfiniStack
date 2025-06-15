package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.tileentity.TileEntityStorageCoreM3; // tileentity\TileEntityStorageCoreM3.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityStorageCoreM3.class)
public abstract class MixinTileEntityStorageCoreM3 {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}