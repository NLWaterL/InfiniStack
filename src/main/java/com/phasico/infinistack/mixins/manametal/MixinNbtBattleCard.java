package com.phasico.infinistack.mixins.manametal;

import project.studio.manametalmod.entity.nbt.NbtBattleCard; // entity\nbt\NbtBattleCard.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(NbtBattleCard.class)
public abstract class MixinNbtBattleCard {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}