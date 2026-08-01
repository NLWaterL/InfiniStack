package com.phasico.infinistack;

import com.phasico.infinistack.client.InstantCraftButtonHandler;
import com.phasico.infinistack.helper.Configurables;
import com.phasico.infinistack.helper.LogHelper;
import com.phasico.infinistack.helper.network.NetworkHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderException;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = InfiniStackMod.MODID, version = "1.1.0-beta3")
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

    //null = not checked yet
    private static Boolean hodgepodgeShiftFixOn;

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
        if (isHodgepodgeShiftFixEnabled()) {
            event.player.addChatMessage(new ChatComponentText(
                    EnumChatFormatting.AQUA + "[InfiniStack] " + EnumChatFormatting.YELLOW
                    + "To avoid potential issues, please set"
                    + " \"fixContainerShiftClickRecursion\" to false under the \"fixes\" category"
                    + " in config/hodgepodge.cfg and restart the game."));
        }
    }

    private static boolean isHodgepodgeShiftFixEnabled() {
        if (hodgepodgeShiftFixOn == null) {
            hodgepodgeShiftFixOn = checkHodgepodgeShiftClickFix();
        }
        return hodgepodgeShiftFixOn;
    }

    private static boolean checkHodgepodgeShiftClickFix() {
        if (!Loader.isModLoaded("hodgepodge")) {
            return false;
        }
        try {
            Class<?> fixesConfig = Class.forName("com.mitchej123.hodgepodge.config.FixesConfig");
            if (fixesConfig.getField("fixContainerShiftClickRecursion").getBoolean(null)) {
                LogHelper.warn("Hodgepodge's fixContainerShiftClickRecursion is enabled;"
                        + " it conflicts with InfiniStack's shift-click handling.");
                return true;
            }
        } catch (Throwable t) {
            LogHelper.error("Could not check Hodgepodge's fixContainerShiftClickRecursion config", t);
        }
        return false;
    }

}
