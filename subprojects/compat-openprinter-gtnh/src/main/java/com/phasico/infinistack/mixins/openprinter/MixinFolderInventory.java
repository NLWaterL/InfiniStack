package com.phasico.infinistack.mixins.openprinter;

import pcl.openprinter.items.FolderInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import com.phasico.infinistack.helper.Configurables;

@Mixin(FolderInventory.class)
@Pseudo
public abstract class MixinFolderInventory {

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

}
