package com.phasico.infinistack.mixins.hbm;

import com.hbm.packet.toserver.AnvilCraftPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AnvilCraftPacket.class)
public interface AccessorAnvilCraftPacket {

    @Accessor("mode")
    int getMode();

    @Accessor("recipeIndex")
    int getIndex();

}
