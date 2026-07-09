package com.phasico.infinistack.mixins.remoteio;

import com.phasico.infinistack.helper.Configurables;
import com.phasico.infinistack.helper.SuppressibleCraftMatrix;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import remoteio.common.inventory.InventoryTileCrafting;

@Mixin(InventoryTileCrafting.class)
@Pseudo
public abstract class MixinInventoryTileCrafting implements SuppressibleCraftMatrix {

    // InventoryTileCrafting re-implements the whole inventory with its own stackList AND its own
    // eventHandler field, constructing super with a null container - so the duck's getEventHandler
    // (which MixinSlotCrafting uses to decide whether to batch) must be overridden to return the
    // real one, set via setContainer(). The flag setter/getter stay inherited from
    // MixinInventoryCrafting's implementation in the superclass.
    @Shadow(remap = false)
    private Container eventHandler;

    @Override
    public Container getEventHandler() {
        return eventHandler;
    }

    @Overwrite(remap = false)
    public int func_70297_j_() {
        return Configurables.maxStackSize;
    }

    // Its overridden decrStackSize/setInventorySlotContents call eventHandler.func_75130_a
    // directly, bypassing the vanilla InventoryCrafting code that MixinInventoryCrafting patches -
    // so its fires get the same suppression check here.
    @Redirect(
        method = {"func_70298_a", "func_70299_a"},
        at = @At(value = "INVOKE",
                 target = "Lnet/minecraft/inventory/Container;func_75130_a(Lnet/minecraft/inventory/IInventory;)V"),
        remap = false
    )
    private void suppressableCraftMatrixChanged(Container handler, IInventory matrix) {
        if (!isMatrixEventsSuppressed()) {
            handler.onCraftMatrixChanged(matrix);
        }
    }
}
