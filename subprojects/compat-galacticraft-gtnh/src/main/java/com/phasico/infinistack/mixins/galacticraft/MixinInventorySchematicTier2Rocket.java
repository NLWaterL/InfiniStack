package com.phasico.infinistack.mixins.galacticraft;

import micdoodle8.mods.galacticraft.planets.mars.inventory.InventorySchematicTier2Rocket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(InventorySchematicTier2Rocket.class)
@Pseudo
public abstract class MixinInventorySchematicTier2Rocket {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
