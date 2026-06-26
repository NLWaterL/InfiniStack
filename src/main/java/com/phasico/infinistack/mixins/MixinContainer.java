package com.phasico.infinistack.mixins;

import com.phasico.infinistack.helper.Configurables;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Set;

@Mixin(Container.class)
public abstract class MixinContainer {

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

    @Shadow
    protected abstract void retrySlotClick(int slotId, int clickedButton, boolean flag, EntityPlayer player);

    @Unique
    private static final ThreadLocal<Integer> RETRY_DEPTH = ThreadLocal.withInitial(() -> 0);

    @Redirect(
        method = "slotClick",
        at = @At(value = "INVOKE",
                 target = "Lnet/minecraft/inventory/Container;retrySlotClick(IIZLnet/minecraft/entity/player/EntityPlayer;)V")
    )
    private void limitedRetrySlotClick(Container self, int slotId, int clickedButton,
                                                    boolean flag, EntityPlayer player) {
        int depth = RETRY_DEPTH.get();
        if (depth >= Configurables.retryLimit - 1) {
            return;
        }
        RETRY_DEPTH.set(depth + 1);
        try {
            retrySlotClick(slotId, clickedButton, flag, player);
        } finally {
            RETRY_DEPTH.set(depth);
        }
    }
}
