package com.phasico.infinistack.mixins.hbm;

import com.hbm.items.tool.ItemAmmoBag; // items\tool\ItemAmmoBag.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Pseudo;

@Pseudo
@Mixin(ItemAmmoBag.class)
public abstract class MixinItemAmmoBag {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}