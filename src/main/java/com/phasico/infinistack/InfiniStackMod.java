package com.phasico.infinistack;

import com.phasico.infinistack.command.InfiniStackCommandGive;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;

@Mod(modid = InfiniStackMod.MODID)
public class InfiniStackMod
{
    public static final String MODID = "infinistack";

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new InfiniStackCommandGive());
    }
}
