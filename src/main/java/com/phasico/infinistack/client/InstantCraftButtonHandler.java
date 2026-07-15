package com.phasico.infinistack.client;

import com.phasico.infinistack.helper.InstantCraftToggle;
import com.phasico.infinistack.mixins.AccessorGuiScreen;
import com.phasico.infinistack.helper.network.MessageInstantCraftToggle;
import com.phasico.infinistack.helper.network.NetworkHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.GuiScreenEvent;

import java.util.Collections;

public class InstantCraftButtonHandler {

    private static boolean stickyPreference = true;

    private GuiScreen trackedScreen;
    private GuiInstantCraftToggleButton trackedButton;

    @SubscribeEvent
    public void onInitGui(GuiScreenEvent.InitGuiEvent.Post event) {
        trackedScreen = null;
        trackedButton = null;

        if (!(event.gui instanceof GuiContainer)) {
            return;
        }

        GuiContainer gui = (GuiContainer) event.gui;
        Container container = gui.inventorySlots;
        if (!(container instanceof InstantCraftToggle)) {
            return;
        }

        InstantCraftToggle toggle = (InstantCraftToggle) container;
        Slot resultSlot = toggle.getResultSlot();
        if (resultSlot == null) {
            return;
        }

        int slotSize = toggle.getResultSlotSize();

        int w;
        int h;

        if (slotSize >= 26) {
            w = 22;
            h = 12;
        } else if (slotSize >= 18) {
            w = 18;
            h = 10;
        } else {
            w = slotSize;
            h = 10;
        }

        int gap = toggle.getButtonGap();

        // Slot x and y from code mean the 16x16 item area; the x formula centers on the frame.
        int x = resultSlot.xDisplayPosition + 8 - w / 2 + toggle.getButtonHorizontalShift();
        int y = toggle.isButtonBelow()
                ? resultSlot.yDisplayPosition + 16 + gap
                : resultSlot.yDisplayPosition - h - gap;

        GuiInstantCraftToggleButton button = new GuiInstantCraftToggleButton(gui, x, y, w, h, container);
        event.buttonList.add(button);

        trackedScreen = event.gui;
        trackedButton = button;

        toggle.setInstantCraftEnabled(stickyPreference);
        sendToggle(stickyPreference);
    }

    @SubscribeEvent
    public void onActionPerformed(GuiScreenEvent.ActionPerformedEvent.Pre event) {
        if (event.button != trackedButton || trackedButton == null) {
            return;
        }

        Container container = trackedButton.container;
        if (container instanceof InstantCraftToggle) {
            InstantCraftToggle toggle = (InstantCraftToggle) container;
            boolean newState = !toggle.isInstantCraftEnabled();
            toggle.setInstantCraftEnabled(newState);
            stickyPreference = newState;
            sendToggle(newState);
        }

        event.setCanceled(true);
        trackedButton.func_146113_a(Minecraft.getMinecraft().getSoundHandler());
    }

    @SubscribeEvent
    public void onDrawScreen(GuiScreenEvent.DrawScreenEvent.Post event) {
        if (event.gui != trackedScreen || trackedButton == null) {
            return;
        }

        if (trackedButton.visible && trackedButton.isHovered(event.mouseX, event.mouseY)) {
            ((AccessorGuiScreen) event.gui).drawHoveringText(
                    Collections.singletonList(trackedButton.isToggledOn()
                            ? "Instant Craft: " + EnumChatFormatting.GREEN + "ON"
                            : "Instant Craft: " + EnumChatFormatting.RED + "OFF"),
                    event.mouseX, event.mouseY);
        }
    }

    private static void sendToggle(boolean enabled) {
        NetworkHandler.instance.sendToServer(new MessageInstantCraftToggle(enabled));
    }
}
