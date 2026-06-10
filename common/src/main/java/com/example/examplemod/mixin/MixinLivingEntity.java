package com.example.examplemod.mixin;

import com.example.examplemod.Constants;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {

    @Inject(method = "causeFallDamage", at = @At("HEAD"))
    private void onFallDamage(float fallDistance, float multiplier,
                              DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof Player player) {
            Constants.LOG.info("{} is taking fall damage from {}m",
                player.getName().getString(), Math.round(fallDistance));
        }
    }
}