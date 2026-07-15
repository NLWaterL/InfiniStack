package com.phasico.infinistack.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.launchwrapper.Launch;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import com.phasico.infinistack.helper.LogHelper;

// Lets users disable individual mixins through plain text files in config/infinistack/,
// without touching the mixin classes themselves.
// mixin_exclusions.txt     - regular mixins (mixins.infinistack.json), filtered here via shouldApplyMixin
// latemixin_exclusions.txt - late mixins, removed from the list in InfiniStackLateMixin.getMixins
public class InfiniStackMixinPlugin implements IMixinConfigPlugin {

    private static final String MIXIN_EXCLUSIONS_FILE = "mixin_exclusions.txt";
    private static final String LATEMIXIN_EXCLUSIONS_FILE = "latemixin_exclusions.txt";

    private static final String[] DEFAULT_MIXIN_EXCLUSIONS = {
            "# InfiniStack mixin exclusions",
            "# One mixin class name per line to disable that mixin, e.g.:",
            "#   MixinTileEntityHopper",
            "# Blank lines and lines starting with # are ignored.",
            "# Changes take effect after a game restart."
    };

    private static final String[] DEFAULT_LATEMIXIN_EXCLUSIONS = {
            "# InfiniStack late mixin exclusions",
            "# One late mixin name per line to disable that mixin, including its package prefix, e.g.:",
            "#   gregtech.MixinBaseMetaTileEntity",
            "#   hbm.MixinContainerAnvil",
            "# Blank lines and lines starting with # are ignored.",
            "# Changes take effect after a game restart."
    };

    private static Set<String> mixinExclusions;
    private static Set<String> lateMixinExclusions;

    // Late mixin names (with package prefix) that InfiniStackLateMixin.getMixins should drop.
    public static Set<String> getLateMixinExclusions() {
        ensureLoaded();
        return lateMixinExclusions;
    }

    // Idempotent so InfiniStackLateMixin can also trigger it, in case getMixins
    // somehow runs before this plugin's onLoad.
    private static synchronized void ensureLoaded() {
        if (mixinExclusions != null) {
            return;
        }
        // Launch.minecraftHome is the game directory; null in some dev environments.
        File gameDir = Launch.minecraftHome != null ? Launch.minecraftHome : new File(".");
        File configDir = new File(gameDir, "config/infinistack");
        mixinExclusions = readExclusionFile(new File(configDir, MIXIN_EXCLUSIONS_FILE), DEFAULT_MIXIN_EXCLUSIONS);
        lateMixinExclusions = readExclusionFile(new File(configDir, LATEMIXIN_EXCLUSIONS_FILE), DEFAULT_LATEMIXIN_EXCLUSIONS);
    }

    private static Set<String> readExclusionFile(File file, String[] defaultContent) {
        Set<String> exclusions = new HashSet<>();
        if (!file.exists()) {
            writeDefaultFile(file, defaultContent);
            return exclusions;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                exclusions.add(line);
            }
        } catch (IOException e) {
            LogHelper.error("Failed to read " + file.getName() + ", no mixins will be excluded by it!", e);
            exclusions.clear();
        }
        if (!exclusions.isEmpty()) {
            LogHelper.info("Loaded {} mixin exclusion(s) from {}", exclusions.size(), file.getName());
        }
        return exclusions;
    }

    private static void writeDefaultFile(File file, String[] defaultContent) {
        File parent = file.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            LogHelper.error("Could not create directory {} for default {}!", parent.getPath(), file.getName());
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : defaultContent) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            LogHelper.error("Failed to create default " + file.getName() + "!", e);
        }
    }

    @Override
    public void onLoad(String mixinPackage) {
        ensureLoaded();
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        ensureLoaded();
        String simpleName = mixinClassName.substring(mixinClassName.lastIndexOf('.') + 1);
        if (mixinExclusions.contains(simpleName)) {
            LogHelper.info("Skipping mixin {} (disabled in {})", simpleName, MIXIN_EXCLUSIONS_FILE);
            return false;
        }
        return true;
    }

    @Override
    public String getRefMapperConfig() { return null; }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() { return null; }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
