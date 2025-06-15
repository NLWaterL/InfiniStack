package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.tileentity.TileEntityChestBox; // tileentity\TileEntityChestBox.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityChestBox.class)
public abstract class MixinTileEntityChestBox {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}