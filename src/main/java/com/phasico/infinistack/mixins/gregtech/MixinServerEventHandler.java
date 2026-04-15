package com.phasico.infinistack.mixins.gregtech;

import bartworks.server.EventHandler.ServerEventHandler;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GTOreDictUnificator;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerEventHandler.class)
@Pseudo
public abstract class MixinServerEventHandler {

    //UNUSED FOR NOW

    @Redirect(
            method = "onPlayerTickEventServer",
            at = @At(
                    value = "INVOKE",
                    target = "Lgregtech/api/util/GTOreDictUnificator;get(Lgregtech/api/enums/OrePrefixes;Ljava/lang/Object;J)Lnet/minecraft/item/ItemStack;"
            ),
            remap = false
    )
    private ItemStack preserveLargeStackOnUnification(OrePrefixes prefix, Object material, long amount) {
        ItemStack result = GTOreDictUnificator.get(prefix, material, 1L);
        if (result != null) {
            result.stackSize = (int) amount;
        }
        return result;
    }

}
