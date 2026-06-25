package com.phasico.infinistack.mixins.magicbees;

import magicbees.tileentity.TileEntityEffectJar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(TileEntityEffectJar.class)
@Pseudo
public abstract class MixinTileEntityEffectJar {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
