package com.phasico.infinipatch.mixins.adventurebackpack;

import com.darkona.adventurebackpack.inventory.ContainerBackpack;
import com.darkona.adventurebackpack.inventory.InventoryCraftingBackpack;
import net.minecraft.inventory.IInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ContainerBackpack.class)
public interface AccessorContainerBackpack {

    @Accessor("craftMatrix")
    InventoryCraftingBackpack getCraftMatrix();

    @Accessor("craftResult")
    IInventory getCraftResult();

    @Invoker("syncCraftMatrixWithInventory")
    void invokeSyncCraftMatrixWithInventory(boolean preCraft);

}
