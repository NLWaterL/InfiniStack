package com.phasico.infinistack.mixins.storagedrawers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import com.jaquadro.minecraft.storagedrawers.storage.DrawerData;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DrawerData.class)
public abstract class MixinDrawerData {

    //If the max stack size is too high it breaks the drawer, so just cap it at 64.

    @Redirect(
            method = "getMaxCapacity(Lnet/minecraft/item/ItemStack;)I",
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
            method = "getStoredItemStackSize",
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

}
