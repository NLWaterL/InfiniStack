package com.phasico.infinistack.mixins;

import com.phasico.infinistack.helper.Configurables;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Slot.class)
public abstract class MixinSlot {

    @Overwrite
    public int getSlotStackLimit(){
        return ((Slot)(Object)this).inventory.getInventoryStackLimit() != 1 ? Configurables.maxStackSize : 1;
    }

}
