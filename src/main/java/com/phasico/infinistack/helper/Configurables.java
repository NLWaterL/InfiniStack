package com.phasico.infinistack.helper;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Configurables {

    public static int maxStackSize;
    public static boolean alwaysShowCount;
    public static boolean enableWorkTable;
    public static boolean useAlternateDisplay;
    
    public static boolean enableFastCraft; //This need to be done for max compact. If it's false, then the crafting table's slot capacity would be limited at 1k.

    public static boolean hideAmbientParticle;

    private static Configuration config;

    public static void init(FMLPreInitializationEvent event) {
        File configFile = new File(event.getModConfigurationDirectory(), "infinistack.cfg");
        config = new Configuration(configFile);

        try {
            config.load();

            maxStackSize = config.getInt("maxStackSize", Configuration.CATEGORY_GENERAL, (1 << 30) - 1, 1, Integer.MAX_VALUE - 10, "Maximum stack size for items.");
            alwaysShowCount = config.getBoolean("alwaysShowCount", Configuration.CATEGORY_GENERAL, false, "Always show the item count in the tooltip, even if shift is not clicked.");
            enableWorkTable = config.getBoolean("enableForestryWorktable", Configuration.CATEGORY_GENERAL, false, "Enable Forestry's Worktable. NOT RECOMMENDED");
            useAlternateDisplay = config.getBoolean("useAlternateDisplay", Configuration.CATEGORY_GENERAL, false, "Use an alternate way to format stack size.");
            
            hideAmbientParticle = config.getBoolean("Hide Ambient Potion Particle", Configuration.CATEGORY_GENERAL, false, "WIP Feature, Don't Change!");

        } catch (Exception e) {
            Logger.error("Failed to load config for Infinistack!");
            e.printStackTrace();
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}
