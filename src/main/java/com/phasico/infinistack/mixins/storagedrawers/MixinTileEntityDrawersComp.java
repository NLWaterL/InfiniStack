package com.phasico.infinistack.mixins.storagedrawers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "com.jaquadro.minecraft.storagedrawers.block.tile.TileEntityDrawersComp$CompCentralInventory")
public abstract class MixinTileEntityDrawersComp {

    @Redirect(
            method = "getMaxCapacity(I)I",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/Item;getItemStackLimit(Lnet/minecraft/item/ItemStack;)I"
            ),
            remap = false
    )
    public int redirectGetItemStackLimit(Item instance, ItemStack itemStack){
        if(itemStack.getItem().getItemStackLimit(itemStack) != 1){
            return 64;
        } else {
            return 1;
        }
    }

    @Redirect(
            method = "getMaxCapacity(ILnet/minecraft/item/ItemStack;)I",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/Item;getItemStackLimit(Lnet/minecraft/item/ItemStack;)I"
            ),
            remap = false
    )
    public int redirectGetItemStackLimit2(Item instance, ItemStack itemStack){
        if(itemStack.getItem().getItemStackLimit(itemStack) != 1){
            return 64;
        } else {
            return 1;
        }
    }

    @Redirect(
            method = "getStoredItemStackSize",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/Item;getItemStackLimit(Lnet/minecraft/item/ItemStack;)I"
            ),
            remap = false
    )
    public int redirectGetItemStackLimit3(Item instance, ItemStack itemStack){
        if(itemStack.getItem().getItemStackLimit(itemStack) != 1){
            return 64;
        } else {
            return 1;
        }
    }

}
