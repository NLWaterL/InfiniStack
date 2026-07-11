package com.phasico.infinistack.mixins.natura;

import com.phasico.infinistack.helper.FixedCraftingContainer;
import mods.natura.gui.WorkbenchContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

@Mixin(WorkbenchContainer.class)
@Pseudo
public abstract class MixinWorkbenchContainer implements FixedCraftingContainer {
}
