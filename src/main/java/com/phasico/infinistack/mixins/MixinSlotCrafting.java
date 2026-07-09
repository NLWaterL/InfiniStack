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

/**
 * Makes one craft cost one recipe search, mimicking how AE2's crafting terminal and the Backpack
 * mod's crafting slots behave.
 *
 * Vanilla onPickupFromSlot mutates the live crafting matrix once per filled slot
 * (decrStackSize, plus setInventorySlotContents for container items like GT tools), and every one
 * of those mutations fires eventHandler.onCraftMatrixChanged - a recipe search. AE2 backs its grid
 * with an inventory whose change hook is a no-op and calls func_75130_a explicitly once per craft
 * (SlotCraftingTerm.craftItem); the Backpack mod's SlotCraftingAdvanced likewise fires it once at
 * the end of func_82870_a. Here we silence the matrix for the duration of onPickupFromSlot and
 * fire the event once on return. All shift-click crafting now runs this per-unit path (driven by
 * MixinContainer's retry loop; InstantCraftingLogic is parked), so together with
 * MixinCraftingManager's memo every unit craft costs one cached recipe check instead of
 * (filled slots + 1) full recipe-list scans - GT tool recipes included.
 *
 * Gated on the container mixin opting in via FixedCraftingContainer. Mod slots that subclass
 * SlotCrafting and override onPickupFromSlot (TConstruct, adventurebackpack, daoza) still work:
 * their super call runs this wrapping, and their own pre/post logic stays outside it unsuppressed.
 */
@Mixin(SlotCrafting.class)
public abstract class MixinSlotCrafting {

    @Shadow
    @Final
    private IInventory craftMatrix;

    // Non-null only between the HEAD and RETURN of an onPickupFromSlot call we chose to batch.
    // Slot instances belong to a single container and crafting runs on the game thread, so a plain
    // field is safe.
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
