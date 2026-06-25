package com.phasico.infinistack.mixins.natura;

import mods.natura.blocks.tech.NetherrackFurnaceLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(NetherrackFurnaceLogic.class)
@Pseudo
public abstract class MixinNetherrackFurnaceLogic {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
