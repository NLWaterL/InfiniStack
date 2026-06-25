package com.phasico.infinistack.mixins.chisel;

import team.chisel.block.tileentity.TileEntityAutoChisel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityAutoChisel.class)
@Pseudo
public abstract class MixinTileEntityAutoChisel {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
