package com.phasico.infinistack;

import com.phasico.infinistack.command.InfiniStackCommandGive;
import com.phasico.infinistack.helper.Configurables;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;

@Mod(modid = InfiniStackMod.MODID)
public class InfiniStackMod
{
    public static final String MODID = "infinistack";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Configurables.init(event);
        }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new InfiniStackCommandGive());
    }
}
