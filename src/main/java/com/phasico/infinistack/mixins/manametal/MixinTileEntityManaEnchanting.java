package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.tileentity.TileEntityManaEnchanting; // tileentity\TileEntityManaEnchanting.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityManaEnchanting.class)
public abstract class MixinTileEntityManaEnchanting {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}