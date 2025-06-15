package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.entity.nbt.NbtMoney; // entity\nbt\NbtMoney.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(NbtMoney.class)
public abstract class MixinNbtMoney {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}