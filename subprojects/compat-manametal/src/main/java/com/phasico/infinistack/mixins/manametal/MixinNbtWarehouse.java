package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.entity.nbt.NbtWarehouse; // entity\nbt\NbtWarehouse.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;


@Mixin(NbtWarehouse.class)
@Pseudo
public abstract class MixinNbtWarehouse {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}