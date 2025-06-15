package com.phasico.infinistack.mixins.avaritiaddons;

import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import wanion.avaritiaddons.block.chest.compressed.TileEntityCompressedChest;

@Mixin(TileEntityCompressedChest.class)
public abstract class MixinTileEntityCompressedChest {

    //For the infinity chest just leave it there. It have a lot of bugs.
    //It eats item if your stack size is bigger than integer max value.

    @ModifyConstant(
            method = "<init>",
            constant = @Constant(intValue = 64),
            remap = false
    )
    private static int modifyMaxStackSize(int original){
        return Configurables.maxStackSize;
    }
}