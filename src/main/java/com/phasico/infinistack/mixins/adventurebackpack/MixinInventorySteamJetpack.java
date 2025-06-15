package com.phasico.infinistack.mixins.adventurebackpack;

import com.darkona.adventurebackpack.inventory.InventorySteamJetpack;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(InventorySteamJetpack.class)
public abstract class MixinInventorySteamJetpack {

    @Overwrite(remap = false)
    public int func_70297_j_()
    {
        return Configurables.maxStackSize;
    }

}
