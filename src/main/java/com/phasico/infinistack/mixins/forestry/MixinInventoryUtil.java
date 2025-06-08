package com.phasico.infinistack.mixins.forestry;

import com.phasico.infinistack.helper.Configurables;
import forestry.core.utils.InventoryUtil;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;
import java.util.Random;

import static forestry.core.utils.InventoryUtil.getStacks;

@Mixin(InventoryUtil.class)
public abstract class MixinInventoryUtil {

    @Redirect(
            method = "dropItemStackFromInventory",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I"
            ), remap = false
    )
    private static int noStackSplit(Random random, int bound) {
        if (bound == 21) {
            return Integer.MAX_VALUE - 10;
        }
        return random.nextInt(bound);
    }

    @Overwrite(remap = false)
    public static boolean containsPercent(IInventory inventory, float percent, int slot1, int length) {
        long amount = 0;
        long stackMax = 0;
        for (ItemStack itemStack : getStacks(inventory, slot1, length)) {
            if (itemStack == null) {
                stackMax += Configurables.maxStackSize;
                continue;
            }

            amount += itemStack.stackSize;
            stackMax += itemStack.getMaxStackSize();
        }
        if (stackMax == 0) {
            return false;
        }
        return ((float) amount / (float) stackMax) >= percent;
    }
}
