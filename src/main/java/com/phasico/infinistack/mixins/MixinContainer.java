package com.phasico.infinistack.mixins;

import com.phasico.infinistack.helper.Configurables;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;

@Mixin(Container.class)
public abstract class MixinContainer {

    @Shadow
    public List inventorySlots = new ArrayList();

    @Shadow
    public abstract ItemStack transferStackInSlot(EntityPlayer p_transferStackInSlot_1_, int p_transferStackInSlot_2_);

    /**
    * Fix float accuracy problem when you left-click and drag an itemStack.
    * Using Int divide instead.
    * @author NLWaterL
    * */
    @Overwrite
    public static void func_94525_a(Set<Slot> slotSet, int dragMode, ItemStack stack, int extra) {
        if (dragMode == 0) {
            if (slotSet != null && !slotSet.isEmpty()) {
                int total = stack.stackSize;
                int size = slotSet.size();

                stack.stackSize = total / size;

                } else {
                stack.stackSize = 0;
            }
        } else if (dragMode == 1) {
            stack.stackSize = 1 + extra;
        }
    }

    /**
     * Fix using shift-click to instant craft super large itemstack will crash the game.
     * There could be better method, but they just break other mod.
     * @author NLWaterL
     * */
    @Overwrite
    protected void retrySlotClick(int p_retrySlotClick_1_, int p_retrySlotClick_2_, boolean p_retrySlotClick_3_, EntityPlayer p_retrySlotClick_4_) {
        for(int i = 0; i < (Configurables.shiftCraftLimit - 1); i++) {
            //Why -1? Because we also need to count the first slotClick call that calls this method!
            ItemStack result = this.noRetrySlotClick(p_retrySlotClick_1_, p_retrySlotClick_4_);
            if(result == null){
                break;
            }
        }
    }

    /**
     * Very simple. Only handle crafting table result shift-click.
     * */
    private ItemStack noRetrySlotClick(int slotId, EntityPlayer player) {
        ItemStack var5 = null;
        ItemStack var17;
            Slot var16;
            if (slotId < 0) {
                    return null;
                }

                var16 = (Slot)this.inventorySlots.get(slotId);
                if (var16 != null && var16.canTakeStack(player)) {
                    var17 = this.transferStackInSlot(player, slotId);
                    if (var17 != null) {
                        var5 = var17.copy();

                        //Removed the retrySlotClick logic here!

                        }
                }
        return var5;
    }
}
