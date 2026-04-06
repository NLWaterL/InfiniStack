package com.phasico.infinistack.mixins;

import com.phasico.infinistack.helper.Configurables;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
@Pseudo
public abstract class MixinItemStack {

    @Shadow public int stackSize;

    @Shadow private Item field_151002_e;
    @Shadow public abstract boolean isItemStackDamageable();
    @Shadow public abstract ItemStack copy();

    @Inject(method = "writeToNBT", at = @At("RETURN"))
    private void afterWrite(NBTTagCompound nbt, CallbackInfoReturnable<NBTTagCompound> cir) {
        nbt.setInteger("Count", this.stackSize);
    }

    @Inject(method = "readFromNBT", at = @At("RETURN"))
    private void afterRead(NBTTagCompound nbt, CallbackInfo ci) {
        if (nbt.hasKey("Count", 3)) { // 3 = int
            ((ItemStack)(Object)this).stackSize = nbt.getInteger("Count");
        } else {
            ((ItemStack)(Object)this).stackSize = nbt.getByte("Count");
        }
    }

    @Overwrite
    public int getMaxStackSize() {
        return ((ItemStack)(Object)this).getItem().getItemStackLimit() == 1 && !Configurables.allStackable ? 1 : Configurables.maxStackSize;
    }

    //Testing Feature

    /*
    @Inject(method = "damageItem", at = @At("HEAD"), cancellable = true)
    private void onDamageItem(int amount, EntityLivingBase entity, CallbackInfo ci) {
        if(!Configurables.allStackable) return;
        if (!(entity instanceof EntityPlayer)) return;
        if (((EntityPlayer) entity).capabilities.isCreativeMode) return;
        if (!this.isItemStackDamageable()) return;
        if (this.stackSize <= 1) return;

        EntityPlayer player = (EntityPlayer) entity;

        ItemStack singleStack = this.copy();
        singleStack.stackSize = 1;

        if (singleStack.attemptDamageItem(amount, entity.getRNG())) {
            entity.renderBrokenItemStack(singleStack);
            player.addStat(StatList.objectBreakStats[Item.getIdFromItem(this.field_151002_e)], 1);

            if (this.field_151002_e instanceof ItemBow) {
                player.destroyCurrentEquippedItem();
            }
        } else {

            //Vanilla logic refuse to merge damaged item, so just do it here. (Easiest way to solve the problem)

            boolean merged = false;
            for (int i = 0; i < player.inventory.mainInventory.length; i++) {
                ItemStack invStack = player.inventory.mainInventory[i];
                if (invStack != null
                        && ItemStack.areItemStackTagsEqual(invStack, singleStack)
                        && singleStack.isItemEqual(invStack)
                        && invStack.stackSize < invStack.getMaxStackSize()) {
                    invStack.stackSize++;
                    merged = true;
                    break;
                }
            }

            if (!merged) {
                if (!player.inventory.addItemStackToInventory(singleStack)) {
                    player.dropPlayerItemWithRandomChoice(singleStack, false);
                }
            }

        }

        --this.stackSize;
        if (this.stackSize < 0) this.stackSize = 0;

        player.inventory.markDirty();

        ci.cancel();
    }

    @Inject(method = "isStackable", at = @At("RETURN"), cancellable = true)
    public void injectIsStackable(CallbackInfoReturnable<Boolean> cir){
        if(Configurables.allStackable){
            cir.setReturnValue(true);
        }
    }*/
}
