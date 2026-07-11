package com.phasico.infinistack.mixins.craftingtweaks;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.blay09.mods.craftingtweaks.CraftingTweaks;
import net.blay09.mods.craftingtweaks.InventoryCraftingCompress;
import net.blay09.mods.craftingtweaks.InventoryCraftingDecompress;
import net.blay09.mods.craftingtweaks.api.TweakProvider;
import net.blay09.mods.craftingtweaks.net.HandlerCompress;
import net.blay09.mods.craftingtweaks.net.MessageCompress;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;

@Mixin(HandlerCompress.class)
@Pseudo
public abstract class MixinHandlerCompress {

    @Overwrite(remap = false)
    public IMessage onMessage(MessageCompress message, MessageContext ctx) {
        EntityPlayerMP entityPlayer = ctx.getServerHandler().playerEntity;
        Container container = entityPlayer.openContainer;
        if (container == null) {
            return null;
        }
        int slotNumber = message.getSlotNumber();
        if (slotNumber < 0 || slotNumber >= container.inventorySlots.size()) {
            return null;
        }
        Slot mouseSlot = (Slot) container.inventorySlots.get(slotNumber);
        if (!(mouseSlot.inventory instanceof InventoryPlayer)) {
            return null;
        }
        ItemStack mouseStack = mouseSlot.getStack();
        if (mouseStack == null) {
            return null;
        }
        TweakProvider provider = CraftingTweaks.instance.getProvider(container);
        if (!CraftingTweaks.compressAnywhere && provider == null) {
            return null;
        }
        if (message.isDecompress()) {
            ItemStack result = CraftingManager.getInstance().findMatchingRecipe((InventoryCrafting) new InventoryCraftingDecompress(container, mouseStack), entityPlayer.worldObj);
            if (result != null && mouseStack.stackSize >= 1) {
                bulkConvert(entityPlayer, mouseSlot, result, 1, message.isCompressAll());
            }
        } else {
            int size = provider != null ? provider.getCraftingGridSize((EntityPlayer) entityPlayer, container, 0) : 9;
            if (size == 9 && mouseStack.stackSize >= 9) {
                ItemStack result = CraftingManager.getInstance().findMatchingRecipe((InventoryCrafting) new InventoryCraftingCompress(container, 3, mouseStack), entityPlayer.worldObj);
                if (result != null) {
                    bulkConvert(entityPlayer, mouseSlot, result, 9, message.isCompressAll());
                } else {
                    result = CraftingManager.getInstance().findMatchingRecipe((InventoryCrafting) new InventoryCraftingCompress(container, 2, mouseStack), entityPlayer.worldObj);
                    if (result != null) {
                        bulkConvert(entityPlayer, mouseSlot, result, 4, message.isCompressAll());
                    }
                }
            } else if (size >= 4 && mouseStack.stackSize >= 4) {
                ItemStack result = CraftingManager.getInstance().findMatchingRecipe((InventoryCrafting) new InventoryCraftingCompress(container, 2, mouseStack), entityPlayer.worldObj);
                if (result != null) {
                    bulkConvert(entityPlayer, mouseSlot, result, 4, message.isCompressAll());
                }
            }
        }
        container.detectAndSendChanges();
        return null;
    }

    @Unique
    private static void bulkConvert(EntityPlayerMP entityPlayer, Slot mouseSlot, ItemStack result, int inputPerCraft, boolean convertAll) {
        int resultPerCraft = result.stackSize;
        ItemStack mouseStack = mouseSlot.getStack();
        if (resultPerCraft <= 0 || mouseStack == null || mouseStack.stackSize < inputPerCraft) {
            return;
        }
        long craftsWanted = convertAll ? mouseStack.stackSize / (long) inputPerCraft : 1L;
        while (craftsWanted > 0) {
            long chunk = Math.min(craftsWanted, Integer.MAX_VALUE / (long) resultPerCraft);
            ItemStack out = result.copy();
            out.stackSize = (int) (chunk * resultPerCraft);
            int attempted = out.stackSize;
            boolean fullyAdded = entityPlayer.inventory.addItemStackToInventory(out);
            int added = fullyAdded ? attempted : attempted - out.stackSize;
            long craftsDone = added / resultPerCraft;
            if (craftsDone > 0) {
                mouseSlot.decrStackSize((int) (craftsDone * inputPerCraft));
            }
            if (!fullyAdded) {
                break; // player inventory is full; stop converting like the original
            }
            craftsWanted -= chunk;
        }
    }
}
