package com.phasico.infinistack.mixins.cfbh;

import net.blay09.mods.cookingforblockheads.container.inventory.InventoryLarge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventoryLarge.class)
@Pseudo
public abstract class MixinInventoryLarge {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
