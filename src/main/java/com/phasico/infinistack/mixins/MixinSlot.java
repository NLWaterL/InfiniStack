package com.phasico.infinistack.mixins;

import net.minecraft.inventory.IInventory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import com.phasico.infinistack.helper.Configurables;
import net.minecraft.inventory.Slot;

@Mixin(Slot.class)
public abstract class MixinSlot {

    @Shadow
    @Final
    public IInventory inventory;

    @Overwrite
    public int getSlotStackLimit()
    {
        if(this.inventory.getInventoryStackLimit() != 1){
           return Configurables.maxStackSize;
        }else{
           return 1;
        }
    }

}
