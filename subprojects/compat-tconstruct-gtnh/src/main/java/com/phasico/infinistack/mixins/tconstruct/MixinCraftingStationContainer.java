package com.phasico.infinistack.mixins.tconstruct;

import com.phasico.infinistack.helper.FixedCraftingContainer;
import com.phasico.infinistack.helper.InstantCraftToggle;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tconstruct.library.modifier.IModifyable;
import tconstruct.tools.inventory.CraftingStationContainer;
import tconstruct.tools.logic.CraftingStationLogic;

import static com.phasico.infinistack.helper.logic.InstantCraftingLogic.*;

@Mixin(CraftingStationContainer.class)
@Pseudo
public abstract class MixinCraftingStationContainer implements FixedCraftingContainer, InstantCraftToggle {

    @Shadow(remap = false)
    public InventoryCrafting craftMatrix;

    @Shadow(remap = false)
    public IInventory craftResult;

    @Shadow(remap = false)
    @Final
    private World worldObj;

    @Shadow(remap = false)
    public CraftingStationLogic logic;

    @Shadow(remap = false)
    protected abstract boolean mergeItemStackRefill(ItemStack stack, int startIndex, int endIndex, boolean useEndIndex);

    @Unique
    private boolean instantCraftEnabled = false;

    @Unique
    private int resultSlotId = -1;

    public boolean isInstantCraftEnabled() {
        return instantCraftEnabled;
    }

    public void setInstantCraftEnabled(boolean enabled) {
        instantCraftEnabled = enabled;
    }

    public Slot getResultSlot() {
        Container self = (Container) (Object) this;
        if (resultSlotId < 0) {
            for (int i = 0; i < self.inventorySlots.size(); i++) {
                if (self.inventorySlots.get(i) instanceof SlotCrafting) {
                    resultSlotId = i;
                    break;
                }
            }
        }
        return resultSlotId < 0 ? null : self.getSlot(resultSlotId);
    }

    public int getResultSlotSize() {
        return 26;
    }

    @Inject(method = "func_82846_b", at = @At("HEAD"), cancellable = true, remap = false)
    private void fastCraftingLogic(EntityPlayer player, int slotIndex, CallbackInfoReturnable<ItemStack> cir) {

        if (!instantCraftEnabled) {
            return;
        }

        if(slotIndex < 0 || slotIndex >= ((Container)(Object)this).inventorySlots.size()){
            return;
        }

        Slot slot = (Slot) ((Container)(Object)this).inventorySlots.get(slotIndex);

        if (slot instanceof SlotCrafting) {
            ItemStack slotStack = slot.getStack();
            IRecipe recipe = findMatchingRecipe(craftMatrix, worldObj);

            if (slotStack != null && recipe != null) {

                if (slotStack.getItem() instanceof IModifyable) {
                    return;
                }

                ItemStack recipeResult = recipe.getCraftingResult(craftMatrix);
                if (recipeResult == null) return;

                int maxCraft = calculateMaxCraft(craftMatrix, 3);
                if (maxCraft <= 0) return;

                long chestSpace = calculateChestRefillSpace(recipeResult);
                long playerSpace = calculateMaxFit(player.inventory, recipeResult, 0, 36);

                long inventorySpace = chestSpace + playerSpace;

                if (inventorySpace <= 0) {
                    return;
                }

                long totalAmount = (long) recipeResult.stackSize * maxCraft;

                if (totalAmount > inventorySpace){

                    long newMaxCraft = (inventorySpace / recipeResult.stackSize);
                    maxCraft = (int)newMaxCraft;
                    totalAmount = (long) recipeResult.stackSize * maxCraft;

                    if (maxCraft <= 0) {
                        return;
                    }
                }

                consumeIngredients(craftMatrix, maxCraft, player, 3);

                long leftOver = refillChestStacks(recipeResult, totalAmount);
                leftOver = returnResult((Container)(Object)this, recipeResult, 10, 46, leftOver);
                returnResultToPlayer(recipeResult, player, leftOver);

                //Achievement & Stuff

                FMLCommonHandler.instance().firePlayerCraftingEvent(player, recipeResult, craftMatrix);
                ((SlotCrafting)slot).onCrafting(recipeResult, (int)totalAmount);

                //Sync server and client

                craftResult.setInventorySlotContents(0, null);

                ((Container)(Object)this).onCraftMatrixChanged(craftMatrix);

                ((Container)(Object)this).detectAndSendChanges();

                cir.setReturnValue(null);

            }
        }
    }

    @Unique
    private long calculateChestRefillSpace(ItemStack stack) {
        if (logic.slotCount == 0 || !stack.isStackable()) {
            return 0;
        }
        Container self = (Container) (Object) this;
        long fit = 0;
        int end = Math.min(46 + logic.slotCount, self.inventorySlots.size());
        for (int i = 46; i < end; i++) {
            Slot chestSlot = (Slot) self.inventorySlots.get(i);
            ItemStack slotStack = chestSlot.getStack();
            if (slotStack != null
                    && stack.isItemEqual(slotStack)
                    && ItemStack.areItemStackTagsEqual(stack, slotStack)) {
                int freeSpace = Math.min(stack.getMaxStackSize(), chestSlot.getSlotStackLimit()) - slotStack.stackSize;
                if (freeSpace > 0) {
                    fit += freeSpace;
                }
            }
        }
        return fit;
    }

    // The original refillChest only tops off existing chest stacks and never places into empty
    // chest slots, so this stays refill-only instead of using returnResult.
    @Unique
    private long refillChestStacks(ItemStack result, long count) {
        if (count <= 0 || logic.slotCount == 0) {
            return count;
        }
        Container self = (Container) (Object) this;
        int end = Math.min(46 + logic.slotCount, self.inventorySlots.size());
        long remaining = count;
        int maxStackSize = result.getMaxStackSize();
        while (remaining > 0) {
            ItemStack batch = result.copy();
            batch.stackSize = (int) Math.min(remaining, maxStackSize);
            int attempted = batch.stackSize;
            mergeItemStackRefill(batch, 46, end, false);
            remaining -= (attempted - batch.stackSize);
            if (batch.stackSize > 0) {
                break;
            }
        }
        return remaining;
    }

    @Unique
    private IRecipe findMatchingRecipe(InventoryCrafting craftMatrix, World world) {
        for (Object recipeObj : CraftingManager.getInstance().getRecipeList()) {
            IRecipe recipe = (IRecipe) recipeObj;
            if (recipe.matches(craftMatrix, world)) {
                return recipe;
            }
        }
        return null;
    }

}
