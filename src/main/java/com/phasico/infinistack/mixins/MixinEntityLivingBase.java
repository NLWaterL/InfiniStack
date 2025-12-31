/*package com.phasico.infinistack.mixins;

import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase {

    @Redirect(method = "updatePotionEffects",
              at = @At(
                      value = "INVOKE",
                      target = "Lnet/minecraft/world/World;spawnParticle(Ljava/lang/String;DDDDDD)V"))
    private void preventAmbientPotionParticles(World world, String particleName , double p_spawnParticle_2_, double p_spawnParticle_4_, double p_spawnParticle_6_, double p_spawnParticle_8_, double p_spawnParticle_10_, double p_spawnParticle_12_) {

        if(!particleName.equals("mobSpellAmbient")){
            world.spawnParticle(particleName, p_spawnParticle_2_, p_spawnParticle_4_, p_spawnParticle_6_, p_spawnParticle_8_, p_spawnParticle_10_, p_spawnParticle_12_);
        }

    }


}*/