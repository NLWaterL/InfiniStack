package com.phasico.infinistack.mixins;

import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import com.phasico.infinistack.helper.Configurables;

@Mixin(NetHandlerPlayServer.class)
public abstract class MixinNetHandlerPlayServer {

    @Shadow
    public EntityPlayerMP playerEntity;

    @Shadow
    private int field_147375_m;

    @Overwrite
    public void processCreativeInventoryAction(C10PacketCreativeInventoryAction p_147344_1_)
    {
        if (this.playerEntity.theItemInWorldManager.isCreative())
        {
            boolean var2 = p_147344_1_.func_149627_c() < 0;
            ItemStack var3 = p_147344_1_.func_149625_d();
            boolean var4 = p_147344_1_.func_149627_c() >= 1 && p_147344_1_.func_149627_c() < 36 + InventoryPlayer.getHotbarSize();
            boolean var5 = var3 == null || var3.getItem() != null;
            boolean var6 = var3 == null || var3.getItemDamage() >= 0 && var3.stackSize <= Configurables.maxStackSize && var3.stackSize > 0;

            if (var4 && var5 && var6)
            {
                if (var3 == null)
                {
                    this.playerEntity.inventoryContainer.putStackInSlot(p_147344_1_.func_149627_c(), (ItemStack)null);
                }
                else
                {
                    this.playerEntity.inventoryContainer.putStackInSlot(p_147344_1_.func_149627_c(), var3);
                }

                this.playerEntity.inventoryContainer.setPlayerIsPresent(this.playerEntity, true);
            }
            else if (var2 && var5 && var6 && this.field_147375_m < 200)
            {
                this.field_147375_m += 20;
                EntityItem var7 = this.playerEntity.dropPlayerItemWithRandomChoice(var3, true);

                if (var7 != null)
                {
                    var7.setAgeToCreativeDespawnTime();
                }
            }
        }
    }
}