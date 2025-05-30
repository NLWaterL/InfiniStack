package com.phasico.infinistack.helper;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Configurables {

    public static int maxStackSize;
    public static boolean isDebugging;
    public static int shiftCraftLimit;

    private static Configuration config;

    public static void init(FMLPreInitializationEvent event) {
        File configFile = new File(event.getModConfigurationDirectory(), "infinistack.cfg");
        config = new Configuration(configFile);

        try {
            config.load();

            maxStackSize = config.getInt("Max Stack Size", Configuration.CATEGORY_GENERAL, (1 << 30) - 1, 1, Integer.MAX_VALUE - 10, "Maximum stack size for items.");
            shiftCraftLimit = config.getInt("Instant Crafting Limit", Configuration.CATEGORY_GENERAL, 3000, 1, 10000, "Each shift-click can proceed how many recipe. WARNING: Values bigger than 5000 may cause lag & potential crash! Don't change it unless you know the consequence!");
            isDebugging = config.getBoolean("Debug Logging", Configuration.CATEGORY_GENERAL, false, "Enable the debug logging.");

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
