package com.phasico.infinistack.mixins.hbm;

import com.hbm.items.tool.ItemCasingBag; // items\tool\ItemCasingBag.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Pseudo;

@Pseudo
@Mixin(ItemCasingBag.class)
public abstract class MixinItemCasingBag {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}