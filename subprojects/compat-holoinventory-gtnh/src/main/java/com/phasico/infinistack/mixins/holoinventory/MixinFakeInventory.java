package com.phasico.infinistack.mixins.holoinventory;

import net.dries007.holoInventory.util.FakeInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(FakeInventory.class)
@Pseudo
public abstract class MixinFakeInventory {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
