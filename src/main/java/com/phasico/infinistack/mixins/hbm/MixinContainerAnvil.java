package com.phasico.infinistack.mixins.hbm;

import com.hbm.inventory.container.ContainerAnvil;
import com.hbm.inventory.recipes.anvil.AnvilRecipes;
import com.hbm.inventory.recipes.anvil.AnvilSmithingRecipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.phasico.infinistack.helper.logic.InstantCraftingLogic;

@Mixin(ContainerAnvil.class)
public abstract class MixinContainerAnvil {

    @Shadow(remap = false)
    protected abstract void updateSmithing();

    @Shadow(remap = false)
    public InventoryBasic input;

    @Shadow(remap = false)
    public IInventory output;

    @Inject(
            method = "func_82846_b",
            at = @At("HEAD"),
            remap = false,
            cancellable = true
    )
    public void injectTransferStackInSlot(EntityPlayer player, int slotId, CallbackInfoReturnable<ItemStack> cir){

        if (slotId == 2) {
            //Normally it is 0 but for HBM Anvil, 2 is output.

            ItemStack left = this.input.getStackInSlot(0);
            ItemStack right = this.input.getStackInSlot(1);

            if(left == null || right == null || this.output.getStackInSlot(0) == null) {
                cir.setReturnValue(null);
            }

            for(AnvilSmithingRecipe rec : AnvilRecipes.getSmithing()) {

                //0-Normal   1-Shapeless(Reverted)   -1 Doesn't match / Error
                int i = rec.matchesInt(left, right);

                if (i != -1){

                    ItemStack recipeOutput = rec.getOutput(left, right);
                    ItemStack output = recipeOutput.copy();

                    int maxCraft = calculateMaxAnvilCraft(left, right, rec);

                    long inventorySpace = InstantCraftingLogic.calculateMaxFit(player.inventory, output);

                    if(maxCraft == -1){

                        if(notConsumeLeft(rec)){

                            //Doesn't consume left, consume right.
                            this.input.decrStackSize(1, rec.amountConsumed(1, i == 1) * right.stackSize);
                            output.stackSize = (int)Math.min(right.stackSize, inventorySpace);
                            InstantCraftingLogic.returnResult(player.inventory, output, player);

                        } else {

                            //Doesn't consume right, consume left.
                            this.input.decrStackSize(0, rec.amountConsumed(0, i == 1) * left.stackSize);
                            output.stackSize = (int)Math.min(left.stackSize, inventorySpace);
                            InstantCraftingLogic.returnResult(player.inventory, output, player);

                        }

                    } else if(maxCraft > 0) {

                        maxCraft = (int)Math.min(maxCraft, inventorySpace);

                        this.input.decrStackSize(0, rec.amountConsumed(0, i == 1) * maxCraft);
                        this.input.decrStackSize(1, rec.amountConsumed(1, i == 1) * maxCraft);

                        output.stackSize = maxCraft;

                        InstantCraftingLogic.returnResult(player.inventory, output, player);

                    }

                    this.updateSmithing();

                    cir.setReturnValue(null);
                }

            }
            cir.setReturnValue(null);
        }
    }

    private static int calculateMaxAnvilCraft(ItemStack leftInput, ItemStack rightInput, AnvilSmithingRecipe recipe) {
        if (leftInput == null || rightInput == null || recipe == null) return -100;

        int maxCraft = 0;

        int leftConsume = recipe.amountConsumed(0, false);
        int rightConsume = recipe.amountConsumed(1, false);

        if (leftConsume < 0 || rightConsume < 0) {
            return -100;
        }

        if (leftConsume == 0 && rightConsume == 0) {
            return -100;   //Really? Is there an infinite crafting recipe.
        }

        //For some mold crafting recipes, use another function.
        if (leftConsume == 0 || rightConsume == 0) {
            return -1;
        }

        int leftLimit = leftInput.stackSize / leftConsume;
        int rightLimit = rightInput.stackSize / rightConsume;

        maxCraft = Math.min(leftLimit, rightLimit);

        return maxCraft;
    }

    private static boolean notConsumeLeft(AnvilSmithingRecipe recipe){
        return recipe.amountConsumed(0, false) == 0;
    }
}
