package com.phasico.infinistack.mixins.bibliocraft;

import jds.bibliocraft.tileentities.TileEntityWeaponRack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityWeaponRack.class)
@Pseudo
public abstract class MixinTileEntityWeaponRack {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
