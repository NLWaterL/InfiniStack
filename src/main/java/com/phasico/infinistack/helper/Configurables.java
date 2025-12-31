package com.phasico.infinistack.helper;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Configurables {

    public static int maxStackSize;
    public static boolean alwaysShowCount;
    public static boolean useAlternateDisplay;

    public static boolean enableFastCraft; //This need to be done for max compact. If it's false, then the crafting table's slot capacity would be limited at 1k.

    public static boolean hideAmbientParticle;

    public static boolean enableWorkTable;
    public static int transmutationLimit;

    private static Configuration config;

    public static void init(FMLPreInitializationEvent event) {

        File configFile = new File(event.getModConfigurationDirectory(), "infinistack.cfg");
        config = new Configuration(configFile);

        try {

            config.load();

            maxStackSize = config.getInt("Max stack size", Configuration.CATEGORY_GENERAL, (1 << 30) - 1, 1, Integer.MAX_VALUE - 10, "Maximum stack size for items");

            alwaysShowCount = config.getBoolean("Always show count", Configuration.CATEGORY_GENERAL, false, "Always show the item count in the tooltip, even if shift is not clicked");
            useAlternateDisplay = config.getBoolean("Use alternate display", Configuration.CATEGORY_GENERAL, true, "Use an alternate way to format stack size");
            enableFastCraft = config.getBoolean("Enable fast craft logic", Configuration.CATEGORY_GENERAL, true, "Use a faster logic for crafting table and some other compatible work stations");

            hideAmbientParticle = config.getBoolean("Hide ambient potion particle", Configuration.CATEGORY_GENERAL, false, "Ambient potion effects will not produce any particles");

            enableWorkTable = config.getBoolean("Enable Forestry worktable", Configuration.CATEGORY_GENERAL, false,"");
            transmutationLimit = config.getInt("Transmutation table limit", Configuration.CATEGORY_GENERAL, 64, 64, 2048, "Amount of item you can take out from a transmutation table in a click");
            
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
