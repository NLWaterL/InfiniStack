package com.phasico.infinistack.main;

import com.phasico.infinistack.helper.Configurables;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InfiniStackMixinPlugin implements IMixinConfigPlugin {

    private static final String AB_PKG = "com.phasico.infinistack.mixins.adventurebackpack.";

    // Mixin classes that belong to the GTNH fork of Adventure Backpack.
    private static final Set<String> GTNH_AB_MIXINS = new HashSet<>(Arrays.asList(
        AB_PKG + "AccessorContainerBackpack",
        AB_PKG + "MixinContainerAdventure",
        AB_PKG + "MixinInventoryAdventure",
        AB_PKG + "MixinTileAdventure"
    ));

    // Mixin classes that belong to the original (non-GTNH) Adventure Backpack.
    private static final Set<String> VANILLA_AB_MIXINS = new HashSet<>(Arrays.asList(
        AB_PKG + "MixinContainerBackpack",
        AB_PKG + "MixinInventoryBackpack",
        AB_PKG + "MixinInventoryCopterPack",
        AB_PKG + "MixinInventorySteamJetpack",
        AB_PKG + "MixinTileAdventureBackpack"
    ));

    // Cached result; null = not yet probed.
    private Boolean adventureBackpackIsGTNH = null;

    private boolean isGTNHVersion(String markerClass) {
        try {
            Class.forName(markerClass);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (GTNH_AB_MIXINS.contains(mixinClassName) || VANILLA_AB_MIXINS.contains(mixinClassName)) {
            if (adventureBackpackIsGTNH == null) {
                adventureBackpackIsGTNH = isGTNHVersion("com.darkona.adventurebackpack.util.ThaumcraftUtils");
            }
            if (GTNH_AB_MIXINS.contains(mixinClassName)) {
                return adventureBackpackIsGTNH;
            } else {
                return !adventureBackpackIsGTNH;
            }
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode node, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode node, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
