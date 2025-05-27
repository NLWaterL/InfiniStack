package com.phasico.infinistack.mixins.hbm;

import com.hbm.config.GeneralConfig;
import com.hbm.hazard.type.HazardTypeRadiation;
import com.hbm.items.ModItems;
import com.hbm.util.BobMathUtil;
import com.hbm.util.ContaminationUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.phasico.infinistack.helper.Configurables;

@Pseudo
@Mixin(HazardTypeRadiation.class)
public abstract class MixinHazardTypeRadiation {

    @Inject(method = "onUpdate", at = @At("HEAD"), cancellable = true, remap = false)
    private void injectOnUpdate(EntityLivingBase target, float level, ItemStack stack, CallbackInfo ci) {

        boolean reacher = false;

        if(target instanceof EntityPlayer)
            reacher = ((EntityPlayer) target).inventory.hasItem(ModItems.reacher);

        int itemSize = Math.min(stack.stackSize, Configurables.maxItemRadiation);

        level *= itemSize;

        if(level > 0) {
            float rad = level / 20F;

            if(GeneralConfig.enable528 && reacher) {
                rad = (float) (rad / 49F);
            } else if(reacher) {
                rad = (float) BobMathUtil.squirt(rad);
            }

            ContaminationUtil.contaminate(target, ContaminationUtil.HazardType.RADIATION, ContaminationUtil.ContaminationType.CREATIVE, rad);
        }
        ci.cancel();
    }
}
