package com.phasico.infinistack.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(value = BlockHopper.class, priority = 999) //Bugtorch does the same thing.
public abstract class MixinBlockHopper extends BlockContainer {

    protected MixinBlockHopper(Material material) {
        super(material);
        }

    @Shadow
    @Final
    private Random field_149922_a;

    @Overwrite
    public void breakBlock(World p_breakBlock_1_, int p_breakBlock_2_, int p_breakBlock_3_, int p_breakBlock_4_, Block p_breakBlock_5_, int p_breakBlock_6_) {

        TileEntityHopper tileentityhopper = (TileEntityHopper) p_breakBlock_1_.getTileEntity(p_breakBlock_2_, p_breakBlock_3_, p_breakBlock_4_);
        if (tileentityhopper != null) {
            for (int i1 = 0; i1 < tileentityhopper.getSizeInventory(); ++i1) {
                ItemStack itemstack = tileentityhopper.getStackInSlot(i1);
                if (itemstack != null) {
                    float f = this.field_149922_a.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.field_149922_a.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.field_149922_a.nextFloat() * 0.8F + 0.1F;

                    EntityItem entityitem = new EntityItem(p_breakBlock_1_, (double) ((float) p_breakBlock_2_ + f), (double) ((float) p_breakBlock_3_ + f1), (double) ((float) p_breakBlock_4_ + f2), itemstack);
                    if (itemstack.hasTagCompound()) {
                        entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                    }

                    float f3 = 0.05F;
                    entityitem.motionX = (double) ((float) this.field_149922_a.nextGaussian() * f3);
                    entityitem.motionY = (double) ((float) this.field_149922_a.nextGaussian() * f3 + 0.2F);
                    entityitem.motionZ = (double) ((float) this.field_149922_a.nextGaussian() * f3);
                    p_breakBlock_1_.spawnEntityInWorld(entityitem);

                }
            }

            p_breakBlock_1_.func_147453_f(p_breakBlock_2_, p_breakBlock_3_, p_breakBlock_4_, p_breakBlock_5_);
        }

        super.breakBlock(p_breakBlock_1_, p_breakBlock_2_, p_breakBlock_3_, p_breakBlock_4_, p_breakBlock_5_, p_breakBlock_6_);
    }

}