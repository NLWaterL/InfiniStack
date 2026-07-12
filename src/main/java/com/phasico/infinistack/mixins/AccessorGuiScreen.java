package com.phasico.infinistack.mixins;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(GuiScreen.class)
public interface AccessorGuiScreen {

    @Invoker(value="func_146283_a")
    void drawHoveringText(List<String> var1, int var2, int var3);
}
