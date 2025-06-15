package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.rpg.TileEntityWeaponIdentification; // rpg\TileEntityWeaponIdentification.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(TileEntityWeaponIdentification.class)
public abstract class MixinTileEntityWeaponIdentification {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}