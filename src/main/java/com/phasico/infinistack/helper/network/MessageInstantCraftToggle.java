package com.phasico.infinistack.helper.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

/**
 * Carries the DESIRED InstantCraft state rather than "flip" so a lost or duplicated
 * message can never leave client and server disagreeing after the next message.
 */
public class MessageInstantCraftToggle implements IMessage {

    private boolean enabled;

    public MessageInstantCraftToggle() {
    }

    public MessageInstantCraftToggle(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.enabled = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.enabled);
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}
