package com.phasico.infinistack.helper;

import net.minecraft.inventory.Container;

/**
 * Duck interface applied to InventoryCrafting by MixinInventoryCrafting.
 *
 * Vanilla InventoryCrafting fires eventHandler.onCraftMatrixChanged() inside every
 * decrStackSize/setInventorySlotContents call, so a single craft of a recipe with N filled slots
 * triggers N+ full recipe lookups. AE2's crafting terminal avoids this by backing its grid with an
 * inventory whose change hook is a no-op and firing exactly one explicit recipe search per craft
 * (ContainerCraftingTerm.onChangeInventory is empty; SlotCraftingTerm.craftItem calls func_75130_a
 * once). This interface lets a crafting slot temporarily silence the matrix the same way.
 */
public interface SuppressibleCraftMatrix {

    /** The Container wired as this matrix's onCraftMatrixChanged listener. */
    Container getEventHandler();

    /** While true, the matrix's mutations do not fire onCraftMatrixChanged. */
    void setSuppressMatrixEvents(boolean suppress);

    /**
     * Read side of the flag, for InventoryCrafting subclasses (RemoteIO, TConstruct, Draconic,
     * Avaritia) that override decrStackSize/setInventorySlotContents with their own tile-backed
     * storage and fire the change event themselves - their mixins redirect those fires through
     * this check.
     */
    boolean isMatrixEventsSuppressed();
}
