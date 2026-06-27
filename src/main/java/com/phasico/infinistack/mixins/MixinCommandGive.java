package com.phasico.infinistack.mixins;

import com.phasico.infinistack.helper.Configurables;
import net.minecraft.command.CommandGive;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(CommandGive.class)
public abstract class MixinCommandGive {

    @ModifyConstant(method = "processCommand", constant = @Constant(intValue = 64))
    private int expandGiveStackLimit(int original) {
        return Configurables.maxStackSize;
    }
}
