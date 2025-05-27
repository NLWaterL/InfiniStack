package com.phasico.infinistack.helper;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import com.phasico.infinistack.helper.Logger;

import java.io.File;

public class Configurables {

    public static int maxStackSize;
    public static boolean isDebugging;
    public static int maxItemRadiation;

    private static Configuration config;

    public static void init(FMLPreInitializationEvent event) {
        File configFile = new File(event.getModConfigurationDirectory(), "infinistack.cfg");
        config = new Configuration(configFile);

        try {
            config.load();

            maxStackSize = config.getInt("Max Stack Size", Configuration.CATEGORY_GENERAL, (1 << 30) - 1, 1, Integer.MAX_VALUE - 10, "Maximum stack size for items.");
            isDebugging = config.getBoolean("Debug Logging", Configuration.CATEGORY_GENERAL, false, "Enable the debug logging.");
            maxItemRadiation = config.getInt("HBM Max Item Size Radiation", Configuration.CATEGORY_GENERAL, (1 << 30) - 1, 64, Integer.MAX_VALUE - 10,
                    "HBM Mod: Max item size that counts for radiation. For example, if you set this to 100, item stacks that have more than 100 item will give you the same amount of radiation as 100 item. ");

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
