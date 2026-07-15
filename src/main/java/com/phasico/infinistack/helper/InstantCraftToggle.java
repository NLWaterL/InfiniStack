package com.phasico.infinistack.helper;

import net.minecraft.inventory.Slot;

public interface InstantCraftToggle {

    boolean isInstantCraftEnabled();

    void setInstantCraftEnabled(boolean enabled);

    Slot getResultSlot();

    int getResultSlotSize();

    default int getButtonGap() {
        int size = getResultSlotSize();
        return (size - 16) / 2 + (size >= 26 ? 2 : 1);
    }

    default int getButtonHorizontalShift() {
        return 0;
    }

    default boolean isButtonBelow() {
        return false;
    }
}
