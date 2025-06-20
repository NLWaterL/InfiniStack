package com.phasico.infinistack;

import com.hbm.config.GeneralConfig;
import com.hbm.handler.HTTPHandler;
import com.hbm.lib.RefStrings;
import com.phasico.infinistack.command.InfiniStackCommandGive;
import com.phasico.infinistack.helper.Configurables;
import com.phasico.infinistack.helper.Logger;
import com.phasico.infinistack.main.InfiniStackLateMixin;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = InfiniStackMod.MODID)
public class InfiniStackMod
{
    public static final String MODID = "infinistack";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        FMLCommonHandler.instance().bus().register(this);
        Configurables.init(event);
        MinecraftForge.EVENT_BUS.register(this);

        }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new InfiniStackCommandGive());
    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if(!event.player.worldObj.isRemote) {

            boolean isGTNH = false;
            boolean patchLoaded = Loader.isModLoaded("infinigtnh");
            try {
                //GTNH fork have this class, but original version doesn't.
                Class.forName("com.darkona.adventurebackpack.util.ThaumcraftUtils");
                isGTNH = true;
            } catch (ClassNotFoundException ignored) {}

                if (isGTNH && !patchLoaded) {

                    event.player.addChatMessage(new ChatComponentText("InfiniStack Warning: You are using the GTNH fork of adventure backpack, but not using InfiniStack's GTNH Compability Mod!"));
                    event.player.addChatMessage(new ChatComponentText("It is recommended to download and use InfiniGTNH."));

                }
        }
    }
}