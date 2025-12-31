package com.phasico.infinistack;

import com.phasico.infinistack.command.InfiniStackCommandGive;
import com.phasico.infinistack.helper.Configurables;
import com.phasico.infinistack.helper.Logger;
import com.phasico.infinistack.helper.ModExtractor;
import com.phasico.infinistack.main.InfiniStackLateMixin;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = InfiniStackMod.MODID, version = "0.1.1-compact")
public class InfiniStackMod
{
    public static final String MODID = "infinistack";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        FMLCommonHandler.instance().bus().register(this);
        Configurables.init(event);
        MinecraftForge.EVENT_BUS.register(this);

        }

    // MOD EXTRACTION BEGINS HERE //

    public boolean needPatch;
    public boolean patchLoaded;
    public boolean needUpdatePatch;
    public boolean success;

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        needPatch = InfiniStackLateMixin.needPatch;

        patchLoaded = Loader.isModLoaded("infinipatch");

        needUpdatePatch = false;

        if(patchLoaded){

            //It will delete newer version, but I'm just trying to make sure that the patch mod is compatible with current version of InfiniStack..
            needUpdatePatch = !ModExtractor.patchVersion.equals(ModExtractor.getModVersion("infinipatch"));

        }

        if (needPatch && (!patchLoaded || needUpdatePatch)) {

            try {
                ModExtractor extractor = new ModExtractor();

                success = extractor.extract(needUpdatePatch);

                if (success) {
                    Logger.info("========================================================================");
                    Logger.info("It is recommended to restart the game / server for better compatibility!");
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new InfiniStackCommandGive());
    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if(!event.player.worldObj.isRemote) {

                if (needPatch && (!patchLoaded || needUpdatePatch)) {

                    event.player.addChatMessage(new ChatComponentText("================================================================================"));

                    if (!success){
                        event.player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "InfiniStack Message: FAILED TO EXTRACT / UPDATE THE PATCH MOD!"));
                        event.player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Please check if you had changed the name of the extracted mod!"));
                    } else {
                        event.player.addChatMessage(new ChatComponentText("InfiniStack Message: It is recommended to restart the game for better compability!"));
                    }

                }

                if (Loader.isModLoaded("infinigtnh")){

                    event.player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "InfiniStack Message: Please delete the InfiniGTNH mod! It is now deprecated!"));

                }

        }
    }
}