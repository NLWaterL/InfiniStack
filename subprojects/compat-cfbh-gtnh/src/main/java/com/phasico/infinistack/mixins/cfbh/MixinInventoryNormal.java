package com.phasico.infinistack.mixins.cfbh;

import net.blay09.mods.cookingforblockheads.container.inventory.InventoryNormal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryNormal.class)
@Pseudo
public abstract class MixinInventoryNormal {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
