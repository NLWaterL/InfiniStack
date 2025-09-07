package com.phasico.infinistack.helper;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Configurables {

    public static int maxStackSize;
    public static boolean alwaysShowCount;
    public static boolean enableWorkTable;

    private static Configuration config;

    public static void init(FMLPreInitializationEvent event) {

        File configFile = new File(event.getModConfigurationDirectory(), "infinistack.cfg");
        config = new Configuration(configFile);

        try {

            config.load();

            maxStackSize = config.getInt("Max Stack Size", Configuration.CATEGORY_GENERAL, (1 << 30) - 1, 1, Integer.MAX_VALUE - 10, "Maximum stack size for items.");
            alwaysShowCount = config.getBoolean("Always Show Stack Size in Tooltip", Configuration.CATEGORY_GENERAL, false, "Always show the item count in the tooltip, even if shift is not clicked.");
            enableWorkTable = config.getBoolean("Enable Forestry's Worktable", Configuration.CATEGORY_GENERAL, false, "Enable Forestry's Worktable. NOT RECOMMENDED");

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
