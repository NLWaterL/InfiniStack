package com.phasico.infinistack.mixins.daoza;

import com.daozcraft.itemsSP.ZipCard;
import com.daozcraft.util.Game;
import com.daozcraft.util.Inv;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;
import java.util.Random;

@Mixin(Inv.class)
@Pseudo
public abstract class MixinInv {

    @Redirect(
            method = "dropTEAllItem",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I"
            ), remap = false
    )
    private static int noStackSplit(Random random, int bound) {
        if (bound == 21) {
            return Integer.MAX_VALUE;
        }
        return random.nextInt(bound);
    }

    @Overwrite(remap = false)
    public static ItemStack joinStack_returnleft_skipA(int pickmode, IInventory inv, ItemStack newadd, int beginslot, int endslot, int skipThisSlot) {
        int a;
        ItemStack sample;
        if (pickmode < 1 || pickmode > 3 || newadd == null || inv.getSizeInventory() < endslot || beginslot < 0 || endslot < 0 || beginslot > endslot) {
            return newadd;
        }
        boolean useZip = newadd.getItem() instanceof ZipCard;
        sample = useZip ? ZipCard.getInnerStack(newadd, true) : newadd;
        if (sample == null || sample.stackSize == 0) {
            return newadd;
        }
        long totalSize = sample.stackSize;
        ArrayList<Integer> sameslotlist = new ArrayList<>();
        int firstEmptySlot = -1;
        boolean haveFullSame = false;
        boolean haveSeries = false;
        for (a = beginslot; a < endslot; ++a) {
            if (skipThisSlot != -1 && a == skipThisSlot) continue;
            ItemStack scaninv = inv.getStackInSlot(a);
            if (scaninv != null) {
                int invSize;
                ItemStack realscan = scaninv.getItem() instanceof ZipCard ? ZipCard.getInnerStack(scaninv, true) : scaninv;
                invSize = realscan != null ? realscan.stackSize : 0;
                if (invSize == 0 || realscan == null || realscan.stackSize == 0) {
                    inv.setInventorySlotContents(a, null);
                    continue;
                }
                if (realscan.getItem() == sample.getItem()) {
                    haveSeries = true;
                    if (!Game.isSameItemMetaTag_skipSize32767(realscan, sample)) continue;
                    if (scaninv.getItem() instanceof ZipCard && !useZip) {
                        useZip = true;
                    }
                    haveFullSame = true;
                    sameslotlist.add(a);
                    totalSize += invSize;
                    continue;
                }
                if (Game.haveSameOreNameCount(realscan, sample) <= 0) continue;
                haveSeries = true;
                continue;
            }
            if (firstEmptySlot != -1) continue;
            firstEmptySlot = a;
        }
        if (haveFullSame && sameslotlist.size() > 0) {
            if (useZip) {
                for(a = 0; a < sameslotlist.size(); ++a) {
                    inv.setInventorySlotContents((Integer)sameslotlist.get(a), (ItemStack)null);
                }

                ItemStack finals = ZipCard.createZipItem((ItemStack)null, sample, (int)totalSize);
                inv.setInventorySlotContents((Integer)sameslotlist.get(0), finals);
                return null;
            } else if ((sample.isStackable() || sample.getMaxStackSize() > 1) && totalSize <= sample.getMaxStackSize()) {
                for (a = 0; a < sameslotlist.size(); ++a) {
                    inv.setInventorySlotContents(((Integer)sameslotlist.get(a)).intValue(), null);
                }
                newadd.stackSize = (int)totalSize;
                inv.setInventorySlotContents(((Integer)sameslotlist.get(0)).intValue(), newadd);
                return null;
            }
            if (sample.isStackable()) {
                for (a = 0; a < sameslotlist.size(); ++a) {
                    int slot = (Integer)sameslotlist.get(a);
                    ItemStack eachSlotstack = inv.getStackInSlot(slot);
                    if (eachSlotstack == null || eachSlotstack.stackSize >= eachSlotstack.getMaxStackSize()) continue;
                    int iosize = Math.min(eachSlotstack.getMaxStackSize() - eachSlotstack.stackSize, sample.stackSize);
                    inv.getStackInSlot((int)slot).stackSize += iosize;
                    inv.markDirty();
                    newadd.stackSize -= iosize;
                    if (newadd.stackSize != 0) continue;
                    return null;
                }
            }
            if (firstEmptySlot != -1) {
                inv.setInventorySlotContents(firstEmptySlot, newadd);
                return null;
            }
            return newadd;
        }
        if ((pickmode == 3 || pickmode == 2 && haveSeries) && firstEmptySlot != -1) {
            inv.setInventorySlotContents(firstEmptySlot, newadd);
            return null;
        }
        return newadd;
    }


}