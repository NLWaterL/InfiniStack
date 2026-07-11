package com.phasico.infinistack.mixins.bogosorter;

import com.cleanroommc.bogosorter.common.sort.ItemSortContainer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ItemSortContainer.class)
@Pseudo
public abstract class MixinItemSortContainer {

    @Shadow(remap = false)
    @Final
    private ItemStack itemStack;

    @Unique
    private long overflow = 0L;

    @Unique
    private long getTotal() {
        return (long) this.itemStack.stackSize + this.overflow;
    }

    @Unique
    private void setTotal(long total) {
        int active = (int) Math.max(0L, Math.min(total, Integer.MAX_VALUE));
        this.itemStack.stackSize = active;
        this.overflow = total - active;
    }

    @Overwrite(remap = false)
    public void shrink(int amount) {
        setTotal(getTotal() - amount);
    }

    @Overwrite(remap = false)
    public void grow(int amount) {
        setTotal(getTotal() + amount);
    }

    @Overwrite(remap = false)
    public int getAmount() {
        return (int) Math.max(0L, Math.min(getTotal(), Integer.MAX_VALUE));
    }

    @Overwrite(remap = false)
    public boolean canMakeStack() {
        return getTotal() > 0L;
    }

    @Overwrite(remap = false)
    public ItemStack makeStack(int max) {
        long total = getTotal();
        int splitAmount = (int) Math.min(max, Math.max(total, 0L));
        setTotal(total - splitAmount);
        ItemStack result = this.itemStack.copy();
        result.stackSize = splitAmount;
        return result;
    }
}
