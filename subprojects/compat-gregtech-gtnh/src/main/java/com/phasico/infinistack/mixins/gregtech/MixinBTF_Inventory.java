package com.phasico.infinistack.mixins.gregtech;

import gtPlusPlus.api.objects.minecraft.BTF_Inventory; // gtPlusPlus\api\objects\minecraft\BTF_Inventory.java
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(BTF_Inventory.class)
@Pseudo
public abstract class MixinBTF_Inventory {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}