package com.phasico.infinistack.mixins;

import com.phasico.infinistack.helper.FixedCraftingContainer;
import com.phasico.infinistack.helper.SuppressibleCraftMatrix;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SlotCrafting.class)
public abstract class MixinSlotCrafting {

    @Shadow
    @Final
    private IInventory craftMatrix;

    @Unique
    private Container batchedEventHandler;

    @Inject(method = "onPickupFromSlot", at = @At("HEAD"))
    private void beginSilentCraft(EntityPlayer player, ItemStack stack, CallbackInfo ci) {
        if (craftMatrix instanceof SuppressibleCraftMatrix) {
            SuppressibleCraftMatrix matrix = (SuppressibleCraftMatrix) craftMatrix;
            Container handler = matrix.getEventHandler();

            if (handler instanceof FixedCraftingContainer) {
                matrix.setSuppressMatrixEvents(true);
                batchedEventHandler = handler;
            }
        }
    }

    @Inject(method = "onPickupFromSlot", at = @At("RETURN"))
    private void endSilentCraft(EntityPlayer player, ItemStack stack, CallbackInfo ci) {
        if (batchedEventHandler != null) {
            ((SuppressibleCraftMatrix) craftMatrix).setSuppressMatrixEvents(false);

            Container handler = batchedEventHandler;
            batchedEventHandler = null;

            handler.onCraftMatrixChanged(craftMatrix);
        }
    }
}
