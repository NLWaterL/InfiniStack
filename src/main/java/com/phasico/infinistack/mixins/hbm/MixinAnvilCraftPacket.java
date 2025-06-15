package com.phasico.infinistack.mixins.hbm;

import com.hbm.inventory.RecipesCommon;
import com.hbm.inventory.container.ContainerAnvil;
import com.hbm.inventory.recipes.anvil.AnvilRecipes;
import com.hbm.packet.toserver.AnvilCraftPacket;
import com.hbm.util.AchievementHandler;
import com.hbm.util.InventoryUtil;
import com.phasico.infinistack.helper.logic.InstantCraftingLogic;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnvilCraftPacket.Handler.class)
public abstract class MixinAnvilCraftPacket {

    @Inject(
            method = "onMessage(Lcom/hbm/packet/toserver/AnvilCraftPacket;Lcpw/mods/fml/common/network/simpleimpl/MessageContext;)Lcpw/mods/fml/common/network/simpleimpl/IMessage;",
            at = @At("HEAD"),
            remap = false,
            cancellable = true
    )
    private void injectOnMessage(AnvilCraftPacket m, MessageContext ctx, CallbackInfoReturnable<IMessage> cir){

        if (((AccessorAnvilCraftPacket)m).getMode() == 1){

            if(((AccessorAnvilCraftPacket)m).getIndex() < 0 || ((AccessorAnvilCraftPacket)m).getIndex() >= AnvilRecipes.getConstruction().size()) {
                cir.setReturnValue(null);
                return;
            }

            EntityPlayer player = ctx.getServerHandler().playerEntity;

            if(!(player.openContainer instanceof ContainerAnvil)) {
                cir.setReturnValue(null);
                return;
            }

            ContainerAnvil anvil = (ContainerAnvil) player.openContainer;
            AnvilRecipes.AnvilConstructionRecipe recipe = AnvilRecipes.getConstruction().get(((AccessorAnvilCraftPacket)m).getIndex());

            if(!recipe.isTierValid(anvil.tier)) {
                cir.setReturnValue(null);
                return;
            }

            //If the output is chance based or cannot stack, just let original logic handle it.
            if(hasChanceResult(recipe) || hasUnstackableResult(recipe)) {
                for(int i = 0; i < 100; i++) {
                    if(InventoryUtil.doesPlayerHaveAStacks(player, recipe.input, true)) {
                        InventoryUtil.giveChanceStacksToPlayer(player, recipe.output);
                        AchievementHandler.fire(player, recipe.output.get(0).stack);
                    } else {
                        break;
                    }
                }
                player.inventoryContainer.detectAndSendChanges();
                cir.setReturnValue(null);
                return;
            }

            int maxCraft = calculateMaxAnvilCraft(player.inventory, recipe);

            //long inventorySpace = InstantCraftingLogic.calculateMaxFit(player.inventory, recipe.output.get(0).stack);

            if(maxCraft <= 0 ){

                cir.setReturnValue(null);
                return;

            }

            consumeAnvilIngredients(player.inventory, maxCraft, recipe);

            for(int i = 0; i < recipe.output.size(); i++) {

                ItemStack finalResult = recipe.output.get(i).stack;

                long resultSize = (long) maxCraft * finalResult.stackSize;

                InstantCraftingLogic.returnBigResult(player.inventory, finalResult, player, resultSize);

            }

            AchievementHandler.fire(player, recipe.output.get(0).stack);
            player.inventoryContainer.detectAndSendChanges();

            cir.setReturnValue(null);
            return;
        }

    }

    private static int calculateMaxAnvilCraft(InventoryPlayer playerInv, AnvilRecipes.AnvilConstructionRecipe recipe){

        int maxCraft = recipe.output.get(0).stack.getMaxStackSize();

        if(playerInv == null){ return 0; }

        for (int i = 0; i < recipe.input.size(); i++) {

            RecipesCommon.AStack recipeStack = recipe.input.get(i);
            long itemCount = getItemCountInInventory(playerInv, recipeStack);
            maxCraft = (int)Math.min(maxCraft, itemCount / recipeStack.stacksize);

        }

        return maxCraft;

    }

    private static void consumeAnvilIngredients(InventoryPlayer playerInv, int maxCraft, AnvilRecipes.AnvilConstructionRecipe recipe){

        if(playerInv == null || maxCraft < 0 || recipe == null){
            return;
        }

        for (int i = 0; i < recipe.input.size(); i++){

            RecipesCommon.AStack recipeStack = recipe.input.get(i);
            long needConsume = (long)recipeStack.stacksize * maxCraft;

            if (needConsume <= 0) continue;

            for (int j = 0; j < 36; j++){

                ItemStack inventoryItem = playerInv.getStackInSlot(j);

                if (inventoryItem == null){ continue; }

                if (recipeStack.matchesRecipe(inventoryItem, false)){


                    if(needConsume >= inventoryItem.stackSize) {
                        needConsume -= inventoryItem.stackSize;
                        playerInv.setInventorySlotContents(j, null);
                        if (needConsume == 0) break;
                    } else {
                        inventoryItem.stackSize -= needConsume;
                        needConsume = 0;
                        break;
                    }
                }
            }

        }

    }

    //HBM uses AStack for its recipes, not storing an ItemStack in it at all. It is completely a different system.
    private static long getItemCountInInventory(InventoryPlayer playerInv, RecipesCommon.AStack stack){

        final ItemStack[] inventory = playerInv.mainInventory;
        long itemCount = 0;

        for(int i = 0; i < 36; i++){

            ItemStack currentItem = inventory[i];

            if(currentItem == null){
                continue;
            }

            //Ignore size when comparing.
            if(stack.matchesRecipe(currentItem, true)){
                itemCount += currentItem.stackSize;
            }
        }

        return itemCount;
    }

    //Maybe make them just variables in the class?
    private static boolean hasChanceResult(AnvilRecipes.AnvilConstructionRecipe recipe) {

        for (int i = 0; i < recipe.output.size(); i++) {

            AnvilRecipes.AnvilOutput outputStack = recipe.output.get(i);
            if(outputStack.chance != 1.0F){
                return true;
            }

        }

        return false;
    }


    private static boolean hasUnstackableResult(AnvilRecipes.AnvilConstructionRecipe recipe){

        for (int i = 0; i < recipe.output.size(); i++) {

            AnvilRecipes.AnvilOutput outputStack = recipe.output.get(i);
            if(outputStack.stack.getMaxStackSize() == 1){
                return true;
            }

        }

        return false;

    }

}
