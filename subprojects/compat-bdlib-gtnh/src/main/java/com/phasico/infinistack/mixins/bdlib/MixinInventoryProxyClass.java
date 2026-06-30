package com.phasico.infinistack.mixins.bdlib;

import com.phasico.infinistack.helper.Configurables;
import net.bdew.lib.tile.inventory.InventoryProxy$class;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(InventoryProxy$class.class)
@Pseudo
public abstract class MixinInventoryProxyClass {

    @ModifyConstant(
            method = "getInventoryStackLimit",
            constant = @Constant(intValue = 64),
            remap = false
    )
    private static int modifyMaxStackSize(int original){
        return Configurables.maxStackSize;
    }

}
