package com.phasico.infinistack.client;

import com.phasico.infinistack.helper.InstantCraftToggle;
import com.phasico.infinistack.mixins.AccessorGuiContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiInstantCraftToggleButton extends GuiButton {

    private static final ResourceLocation WIDGETS = new ResourceLocation("textures/gui/widgets.png");

    public final Container container;

    private final GuiContainer gui;
    private final int localX;
    private final int localY;

    public GuiInstantCraftToggleButton(GuiContainer gui, int localX, int localY,
                                       int width, int height, Container container) {
        super(-1, 0, 0, width, height, "I");
        this.gui = gui;
        this.localX = localX;
        this.localY = localY;
        this.container = container;
        updatePosition();
    }

    private void updatePosition() {
        xPosition = ((AccessorGuiContainer) gui).getGuiLeft() + localX;
        yPosition = ((AccessorGuiContainer) gui).getGuiTop() + localY;
    }

    public boolean isToggledOn() {
        return container instanceof InstantCraftToggle
                && ((InstantCraftToggle) container).isInstantCraftEnabled();
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= xPosition && mouseY >= yPosition
                && mouseX < xPosition + width && mouseY < yPosition + height;
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        updatePosition();
        return super.mousePressed(mc, mouseX, mouseY);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (!visible) {
            return;
        }

        updatePosition();

        mc.getTextureManager().bindTexture(WIDGETS);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        boolean hovered = isHovered(mouseX, mouseY);
        int v = 46 + getHoverState(hovered) * 20;

        int leftWidth = width / 2;
        int rightWidth = width - leftWidth;
        int topHeight = height / 2;
        int bottomHeight = height - topHeight;

        drawTexturedModalRect(xPosition, yPosition, 0, v, leftWidth, topHeight);
        drawTexturedModalRect(xPosition + leftWidth, yPosition, 200 - rightWidth, v, rightWidth, topHeight);
        drawTexturedModalRect(xPosition, yPosition + topHeight, 0, v + 20 - bottomHeight, leftWidth, bottomHeight);
        drawTexturedModalRect(xPosition + leftWidth, yPosition + topHeight, 200 - rightWidth, v + 20 - bottomHeight, rightWidth, bottomHeight);

        int color = isToggledOn() ? 0x55FF55 : 0xA0A0A0;
        drawCenteredString(mc.fontRenderer, displayString, xPosition + width / 2, yPosition + (height - 8) / 2, color);
    }
}
