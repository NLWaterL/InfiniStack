package com.phasico.infinistack.helper.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class NetworkHandler {

    public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel("infinistack");

    public static void init() {
        instance.registerMessage(HandlerInstantCraftToggle.class, MessageInstantCraftToggle.class, 0, Side.SERVER);
    }
}
