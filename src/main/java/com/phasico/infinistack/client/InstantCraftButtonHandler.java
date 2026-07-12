package com.phasico.infinistack.client;

import com.phasico.infinistack.helper.InstantCraftToggle;
import com.phasico.infinistack.mixins.AccessorGuiScreen;
import com.phasico.infinistack.helper.network.MessageInstantCraftToggle;
import com.phasico.infinistack.helper.network.NetworkHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.GuiScreenEvent;

import java.util.Collections;

public class InstantCraftButtonHandler {

    private static boolean stickyPreference = false;

    private GuiScreen trackedScreen;
    private GuiInstantCraftToggleButton trackedButton;

    @SubscribeEvent
    public void onInitGui(GuiScreenEvent.InitGuiEvent.Post event) {
        trackedScreen = null;
        trackedButton = null;

        int w;
        int h;
        int gap;

        int resultSlotId = 0; //Normally 0, but if it's not, re-assign the correct value to it in the if-else part.

        // Slot x and y from code means the 16x16 item area, so for a frame of FxG px centered on the item area:
        // gap = (G - 16) / 2 + air, and the x formula below already centers on the frame.
        if (event.gui instanceof GuiCrafting) {
            w = 22;
            h = 12;
            gap = 7;  // 26x26 result-slot frame: (26-16)/2 + 2px air
        } else if (event.gui instanceof GuiInventory) {
            w = 18;
            h = 10;
            gap = 2;  // normal 18x18 frame: (18-16)/2 + 1px air
        } else {
            return;
        }

        GuiContainer gui = (GuiContainer) event.gui;
        Container container = gui.inventorySlots;
        if (!(container instanceof InstantCraftToggle)) {
            return;
        }

        Slot resultSlot = container.getSlot(resultSlotId);
        int x = resultSlot.xDisplayPosition + 8 - w / 2;
        int y = resultSlot.yDisplayPosition - h - gap;

        GuiInstantCraftToggleButton button = new GuiInstantCraftToggleButton(gui, x, y, w, h, container);
        event.buttonList.add(button);

        trackedScreen = event.gui;
        trackedButton = button;

        ((InstantCraftToggle) container).setInstantCraftEnabled(stickyPreference);
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
