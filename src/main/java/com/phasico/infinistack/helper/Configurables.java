package com.phasico.infinistack.helper;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Configurables {

    public static int maxStackSize;
    public static boolean allStackable;

    public static boolean alwaysShowCount;
    public static boolean useAlternateDisplay;
    //null = auto
    public static Boolean resizeCountTextOverride;
    
    public static int transmutationLimit;
    public static int retryLimit;

    private static Configuration config;

    public static void init(FMLPreInitializationEvent event) {
    	
        File configFile = new File(event.getModConfigurationDirectory(), "infinistack.cfg");
        config = new Configuration(configFile);

        try {
        	
            config.load();

            maxStackSize = config.getInt("Max stack size", Configuration.CATEGORY_GENERAL, 1_000_000_000, 1, Integer.MAX_VALUE, "Maximum stack size for items");
            allStackable = config.getBoolean("Stack everything", Configuration.CATEGORY_GENERAL, false, "Make everything stackable, even items with stack size 1");

            String[] stackSizeOverrides = config.getStringList("Stack size overrides", Configuration.CATEGORY_GENERAL, new String[0],
                    "Per-item max stack size overrides. These items keep the given size instead of the global maximum.\n"
                    + "Format: <modid>:<unlocalized / registry name>:<metadata> <stack size>\n"
                    + "Use * as a metadata wildcard (Or leave it blank).\n"
                    + "Examples:\n"
                    + "  htx:item.micacap:30010 16\n"
                    + "  htx:item.micacap:* 16\n"
                    + "  minecraft:ender_pearl 16");
            StackSizeOverrides.load(stackSizeOverrides);

            alwaysShowCount = config.getBoolean("Always show count", Configuration.CATEGORY_GENERAL, false, "Always show the item count in the tooltip, even if shift is not clicked");
            useAlternateDisplay = config.getBoolean("Use alternate display", Configuration.CATEGORY_GENERAL, false, "Format stack sizes like ModularUI");

            String resizeCountText = config.getString("Resize count text", Configuration.CATEGORY_GENERAL, "auto", "Scale down longer stack size texts. Type true or false to toggle this.");
            if ("true".equalsIgnoreCase(resizeCountText)) {
                resizeCountTextOverride = Boolean.TRUE;
            } else if ("false".equalsIgnoreCase(resizeCountText)) {
                resizeCountTextOverride = Boolean.FALSE;
            } else {
                resizeCountTextOverride = null; //auto
            }

            transmutationLimit = config.getInt("Transmutation table limit", Configuration.CATEGORY_GENERAL, 64, 1, Integer.MAX_VALUE, "Amount of item you can take out from a transmutation table in a click");
            retryLimit = config.getInt("Shift-Click recursion limit", Configuration.CATEGORY_GENERAL, 10000, 1, Integer.MAX_VALUE, "Amount of item you can craft in one shift-click");

        } catch (Exception e) {
            LogHelper.error("Failed to load config for InfiniStack!", e);
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}
