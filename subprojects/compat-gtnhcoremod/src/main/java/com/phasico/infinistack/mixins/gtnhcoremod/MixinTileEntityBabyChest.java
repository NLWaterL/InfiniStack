package com.phasico.infinistack.mixins.gtnhcoremod;

import com.dreammaster.modbabychest.TileEntityBabyChest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityBabyChest.class)
@Pseudo
public abstract class MixinTileEntityBabyChest {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
