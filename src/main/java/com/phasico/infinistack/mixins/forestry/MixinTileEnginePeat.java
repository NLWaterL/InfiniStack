package com.phasico.infinistack.mixins.forestry;

import com.phasico.infinistack.helper.Configurables;
import forestry.energy.tiles.TileEnginePeat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Constant;

@Mixin(TileEnginePeat.class)
public abstract class MixinTileEnginePeat {

    @ModifyConstant(
            method = "getFreeWasteSlot",
            constant = @Constant(intValue = 64),
            remap = false
    )
    private int modifyDefaultStackLimit(int original) {
        return Configurables.maxStackSize;
    }

}
