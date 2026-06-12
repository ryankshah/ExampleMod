package com.example.examplemod.enchantment;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record TeleportAttackerEffect(LevelBasedValue maxDistance)
        implements EnchantmentEntityEffect {

    public static final MapCodec<TeleportAttackerEffect> CODEC =
        RecordCodecBuilder.mapCodec(instance -> instance.group(
            LevelBasedValue.CODEC.fieldOf("max_distance").forGetter(TeleportAttackerEffect::maxDistance)
        ).apply(instance, TeleportAttackerEffect::new));

    @Override
    public void apply(ServerLevel level, int enchantmentLevel,
                      EnchantedItemInUse item, Entity entity, Vec3 origin) {
        if (entity instanceof ServerPlayer player) {
            double range = maxDistance.calculate(enchantmentLevel);
            // teleport logic here
        }
    }

    @Override
    public MapCodec<TeleportAttackerEffect> codec() {
        return CODEC;
    }
}