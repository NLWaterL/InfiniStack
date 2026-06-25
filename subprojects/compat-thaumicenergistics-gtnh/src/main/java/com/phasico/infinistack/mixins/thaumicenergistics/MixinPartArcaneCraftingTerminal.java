package com.phasico.infinistack.mixins.thaumicenergistics;

import thaumicenergistics.common.parts.PartArcaneCraftingTerminal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(PartArcaneCraftingTerminal.class)
@Pseudo
public abstract class MixinPartArcaneCraftingTerminal {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
