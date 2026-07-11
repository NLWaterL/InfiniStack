package com.phasico.infinistack.helper;

import net.minecraft.inventory.Container;

public interface SuppressibleCraftMatrix {

    Container getEventHandler();

    void setSuppressMatrixEvents(boolean suppress);

    boolean isMatrixEventsSuppressed();
}
