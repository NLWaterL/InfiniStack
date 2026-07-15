package com.phasico.infinistack;

import com.phasico.infinistack.client.InstantCraftButtonHandler;
import com.phasico.infinistack.helper.Configurables;
import com.phasico.infinistack.helper.network.NetworkHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderException;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = InfiniStackMod.MODID, version = "1.1.0-beta2")
public class InfiniStackMod
{
    public static final String MODID = "infinistack";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if (Loader.isModLoaded("infinipatch")) {
            throw new LoaderException(
                "InfiniPatch is deprecated and must be manually removed. " +
                "Please delete the mod named \"InfiniPatch-[EXTRACTED]\" from your mods folder.");
        }
        FMLCommonHandler.instance().bus().register(this);
        Configurables.init(event);
        MinecraftForge.EVENT_BUS.register(this);

        if (event.getSide().isClient()) {
            MinecraftForge.EVENT_BUS.register(new InstantCraftButtonHandler());
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkHandler.init();
    }

}