package com.phasico.infinistack.mixins.bdlib;

import com.phasico.infinistack.helper.Configurables;
import net.bdew.lib.tile.inventory.BaseInventory;
import net.bdew.lib.tile.inventory.BaseInventory$class;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

@Mixin(BaseInventory$class.class)
@Pseudo
public abstract class MixinBaseInventoryClass {

    @Overwrite(remap = false)
    public static int getInventoryStackLimit(BaseInventory $this){
        return Configurables.maxStackSize;
    }
}
