package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.rpg.TileEntityWeaponScroll; // rpg\TileEntityWeaponScroll.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;


@Mixin(TileEntityWeaponScroll.class)
@Pseudo
public abstract class MixinTileEntityWeaponScroll {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}