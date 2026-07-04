package com.phasico.infinistack.mixins.draconic;

import com.brandon3055.draconicevolution.common.tileentities.TileDraconiumChest;
import com.phasico.infinistack.helper.Configurables;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.brandon3055.draconicevolution.common.container.ContainerDraconiumChest;

import static com.phasico.infinistack.helper.logic.InstantCraftingLogic.*;


@Mixin(ContainerDraconiumChest.class)
@Pseudo
public abstract class MixinContainerDraconiumChest {

    @Shadow(remap = false)
    public InventoryCrafting craftMatrix;

    @Shadow(remap = false)
    public IInventory craftResult;

    @Shadow(remap = false)
    private World worldObj;

    @Shadow(remap = false)
    private TileDraconiumChest tile;


    @Inject(method = "func_82846_b", at = @At("HEAD"), cancellable = true, remap = false)
    private void fastCraftingLogic(EntityPlayer player, int slotIndex, CallbackInfoReturnable<ItemStack> cir) {

        if (!Configurables.enableFastCraft) {
            return;
        }

        if(slotIndex < 0 || slotIndex >= ((Container)(Object)this).inventorySlots.size()){
            return;
        }

        Slot slot = (Slot) ((Container) (Object) this).inventorySlots.get(slotIndex);

        if (slot instanceof SlotCrafting) {
            ItemStack slotStack = slot.getStack();
            IRecipe recipe = findMatchingRecipe(craftMatrix, worldObj);

            if (slotStack != null && recipe != null) {

                ItemStack recipeResult = recipe.getCraftingResult(craftMatrix);
                if (recipeResult == null) return;

                int maxCraft = calculateMaxCraft(craftMatrix, 3);
                if (maxCraft <= 0) return;

                long chestSpace = calculateMaxFit((Container)(Object)this, recipeResult, 0, this.tile.func_70302_i_());
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

                consumeIngredients(craftMatrix, maxCraft, 3);

                long leftOver = returnResult(player.inventoryContainer, recipeResult, 9, 45, totalAmount);
                leftOver = returnResult((Container)(Object)this, recipeResult, 0, this.tile.func_70302_i_(), leftOver);
                returnResultToPlayer(recipeResult, player, leftOver);

                //Achievement & Stuff

                FMLCommonHandler.instance().firePlayerCraftingEvent(player, recipeResult, craftMatrix);
                ((SlotCrafting)slot).onCrafting(recipeResult, maxCraft);

                //Sync server and client

                craftResult.setInventorySlotContents(0, null);

                ((Container) (Object) this).onCraftMatrixChanged(craftMatrix);

                ((Container) (Object) this).detectAndSendChanges();

                cir.setReturnValue(null);

            }
        }

    }

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
