package com.phasico.infinistack.helper;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import net.minecraft.client.Minecraft;

import java.io.*;

public class ModExtractor {

    //Please don't forget to update patch version when the patch has updated.
    public static final String patchVersion = "0.1.0";

    public ModExtractor(){}

    public boolean extract(boolean isUpdating) {

        File modsDir = getModsDirectory();
        File targetFile = new File(modsDir, "InfiniPatch-[EXTRACTED].jar");

        if (!targetFile.exists() && isUpdating){

            //It exist but the name has changed
            Logger.warn("Did the user changed the name of the extracted mod? Cannot find the mod to update!");
            return false;

        }

        if (targetFile.exists() && isUpdating) {
            boolean success = targetFile.delete();
            if (!success){
                Logger.warn("Cannot delete the older version of patch!");
                return false;
            }
        }

        try (InputStream in = getClass().getResourceAsStream("/patch/InfiniPatch-[EXTRACTED].jar");
             OutputStream out = new FileOutputStream(targetFile)) {

            if (in == null) {
                Logger.warn("Mod not found: patch/InfiniPatch-[EXTRACTED].jar");
                return false;
            }

            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            if (!targetFile.exists() || targetFile.length() == 0) {
                Logger.warn("Patch mod extraction failed - file not created properly");
                return false;
            }

            Logger.info("Patch mod extraction completed!");
            return true;

            } catch (IOException e) {

            Logger.warn("Cannot extract the compact mod from the main mod!");

            e.printStackTrace();

            return false;

            }
    }

    public static String getModVersion (String modid){
        ModContainer mod = Loader.instance().getIndexedModList().get(modid);
        if (mod != null) {
            return mod.getVersion();
        }
        return null;
    }

    public static File getModsDirectory() {

        if (FMLCommonHandler.instance().getSide().isClient()) {
            return new File(Minecraft.getMinecraft().mcDataDir, "mods/");
        }

        else if (FMLCommonHandler.instance() != null &&
                FMLCommonHandler.instance().getMinecraftServerInstance() != null) {
            return new File(FMLCommonHandler.instance()
                    .getMinecraftServerInstance()
                    .getFile(""), "mods/");
        }

        else {

            //Get the mods dir from config dir......
            return new File(Loader.instance().getConfigDir().getParentFile(), "mods/");

        }
    }

}
