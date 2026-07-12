package com.phasico.infinistack.helper.network;

import com.phasico.infinistack.helper.InstantCraftToggle;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;

public class HandlerInstantCraftToggle implements IMessageHandler<MessageInstantCraftToggle, IMessage> {

    @Override
    public IMessage onMessage(MessageInstantCraftToggle message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().playerEntity;
        Container container = player.openContainer;
        if (container instanceof InstantCraftToggle) {
            ((InstantCraftToggle) container).setInstantCraftEnabled(message.isEnabled());
        }
        return null;
    }
}
