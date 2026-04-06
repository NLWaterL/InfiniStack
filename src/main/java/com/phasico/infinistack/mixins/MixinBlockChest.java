package com.phasico.infinistack.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(value = BlockChest.class, priority = 999) //Bugtorch does the same thing
public abstract class MixinBlockChest extends BlockContainer {

    protected MixinBlockChest(Material material) {
        super(material);
        }

    @Shadow
    @Final
    private Random field_149955_b;

    @Overwrite
    public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {

        TileEntityChest tileentitychest = (TileEntityChest) p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
        if (tileentitychest != null) {
            for (int i1 = 0; i1 < tileentitychest.getSizeInventory(); ++i1) {
                ItemStack itemstack = tileentitychest.getStackInSlot(i1);
                if (itemstack != null) {
                    float f = this.field_149955_b.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.field_149955_b.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.field_149955_b.nextFloat() * 0.8F + 0.1F;

                    EntityItem entityitem = new EntityItem(p_149749_1_, (double) ((float) p_149749_2_ + f), (double) ((float) p_149749_3_ + f1), (double) ((float) p_149749_4_ + f2), itemstack);
                    if (itemstack.hasTagCompound()) {
                        entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                    }

                    float f3 = 0.05F;
                    entityitem.motionX = (double) ((float) this.field_149955_b.nextGaussian() * f3);
                    entityitem.motionY = (double) ((float) this.field_149955_b.nextGaussian() * f3 + 0.2F);
                    entityitem.motionZ = (double) ((float) this.field_149955_b.nextGaussian() * f3);
                    p_149749_1_.spawnEntityInWorld(entityitem);

                }
            }

            p_149749_1_.func_147453_f(p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_);
        }

        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
    }

}
