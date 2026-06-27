package com.phasico.infinistack.mixins.harvestcraft;

import com.pam.harvestcraft.SlotPamFishTrap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(SlotPamFishTrap.class)
@Pseudo
public abstract class MixinSlotPamFishTrap {

    @Overwrite(remap = false)
    public int func_75219_a() {
        return Configurables.maxStackSize;
    }

}
