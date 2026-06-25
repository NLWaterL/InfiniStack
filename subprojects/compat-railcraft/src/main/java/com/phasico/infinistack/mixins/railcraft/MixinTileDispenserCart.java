package com.phasico.infinistack.mixins.railcraft;

import mods.railcraft.common.blocks.machine.gamma.TileDispenserCart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileDispenserCart.class)
@Pseudo
public abstract class MixinTileDispenserCart {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
