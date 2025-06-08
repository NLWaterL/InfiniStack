package com.phasico.infinistack.mixins.forestry;

import com.phasico.infinistack.helper.Configurables;
import forestry.core.blocks.BlockBase;
import forestry.core.tiles.TileBase;
import forestry.factory.tiles.TileWorktable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBase.class)
public abstract class MixinBlockBase {

    @Inject(
            method = "func_149727_a",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private void injectOnBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9, CallbackInfoReturnable<Boolean> cir){

        TileBase tile = (TileBase) world.getTileEntity(x, y, z);

        //I literally tried every way to add compact, but it just doesn't work.
        //You can still get the item inside by breaking the block.

        if (tile instanceof TileWorktable && !Configurables.enableWorkTable){
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "InfiniStack: Forestry Worktable causes severe conflict with InfiniStack."));
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "To use it, you must enable it in the config."));
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "This is NOT RECOMMENDED because it causes item lost and crashes."));
            player.addChatMessage(new ChatComponentText(                          "------------------------------------------------------------------------"));
            cir.setReturnValue(false);

        }

    }

}
