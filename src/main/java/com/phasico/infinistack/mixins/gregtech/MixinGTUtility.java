package com.phasico.infinistack.mixins.gregtech;

import com.phasico.infinistack.helper.Configurables;
import gregtech.api.util.GTUtility;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

import static gregtech.api.util.GTUtility.copy;
import static gregtech.api.util.GTUtility.isStackInvalid;

@Mixin(GTUtility.class)
@Pseudo
public abstract class MixinGTUtility {

    @Overwrite(remap = false)
    public static ItemStack copyAmount(int aAmount, ItemStack aStack) {
        ItemStack rStack = copy(aStack);
        if (isStackInvalid(rStack)) {
            return null;
        } else {
            if (aAmount > Configurables.maxStackSize) {
                aAmount = Configurables.maxStackSize;
            } else if ( aAmount != -1 && aAmount < 0) {
                aAmount = 0;
            }

            rStack.stackSize = aAmount;
            return rStack;
        }
    }

    @Overwrite(remap = false)
    public static ItemStack multiplyStack(int aMultiplier, ItemStack aStack) {
        ItemStack rStack = copy(aStack);
        if (isStackInvalid(rStack)) {
            return null;
        } else {
            long tAmount = rStack.stackSize * (long)aMultiplier;
            if (tAmount > Configurables.maxStackSize) {
                tAmount = Configurables.maxStackSize;
            } else if ( tAmount != -1 && tAmount < 0) {
                tAmount = 0;
            }

            rStack.stackSize = (int)tAmount;
            return rStack;
        }
    }

}
