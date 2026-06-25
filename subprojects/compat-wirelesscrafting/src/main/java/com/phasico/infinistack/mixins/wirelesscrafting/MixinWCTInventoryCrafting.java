package com.phasico.infinistack.mixins.wirelesscrafting;

import net.p455w0rd.wirelesscraftingterminal.common.inventory.WCTInventoryCrafting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(WCTInventoryCrafting.class)
@Pseudo
public abstract class MixinWCTInventoryCrafting {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
