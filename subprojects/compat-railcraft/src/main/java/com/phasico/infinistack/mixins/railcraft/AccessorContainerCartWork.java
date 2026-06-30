package com.phasico.infinistack.mixins.railcraft;

import mods.railcraft.common.gui.containers.ContainerCartWork;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ContainerCartWork.class)
public interface AccessorContainerCartWork {

    @Accessor("craftMatrix")
    InventoryCrafting getCraftMatrix();

    @Accessor("craftResult")
    IInventory getCraftResult();

}
