package com.phasico.infinistack.mixins;

import com.phasico.infinistack.helper.Configurables;
import net.minecraft.network.NetHandlerPlayServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(NetHandlerPlayServer.class)
@Pseudo
public abstract class MixinNetHandlerPlayServer {

    @ModifyConstant(method = "processCreativeInventoryAction", constant = @Constant(intValue = 64))
    private int expandCreativeStackLimit(int original) {
        return Configurables.maxStackSize;
    }
}
