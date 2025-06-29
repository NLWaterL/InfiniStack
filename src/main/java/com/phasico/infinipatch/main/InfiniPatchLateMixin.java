package com.phasico.infinipatch.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;

// Define late mixins (mixins targetting non-coremod mod classes) in this class.
// These mixins get loaded after mod classes are put on the classpath, allowing you to mix into them.
@LateMixin
public class InfiniPatchLateMixin implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.infinipatch.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        List<String> mixins = new ArrayList<>();

        if (loadedMods.contains("adventurebackpack")){
            boolean isGTNH = false;
            try {
                //GTNH fork have this class, but original version doesn't.
                Class.forName("com.darkona.adventurebackpack.util.ThaumcraftUtils");
                isGTNH = true;
            } catch (ClassNotFoundException ignored) {}

            if (isGTNH){
                List<String> adventureBackpackMixins = Arrays.asList(
                        "AccessorContainerBackpack",
                        "MixinContainerAdventure",
                        "MixinInventoryAdventure",
                        "MixinTileAdventure"
                );

                for (String mixinClass : adventureBackpackMixins) {
                    mixins.add("adventurebackpack." + mixinClass);
                }

            }
        }

        return mixins;
    }

}