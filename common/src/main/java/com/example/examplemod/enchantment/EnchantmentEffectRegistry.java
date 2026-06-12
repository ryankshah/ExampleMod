package com.example.examplemod.enchantment;

import com.example.examplemod.Constants;
import com.example.examplemod.registration.RegistrationProvider;
import com.example.examplemod.registration.RegistryObject;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;

public class EnchantmentEffectRegistry {

    public static final RegistrationProvider<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_EFFECTS =
            RegistrationProvider.get(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Constants.MOD_ID);

    public static final RegistryObject<
                MapCodec<? extends EnchantmentEntityEffect>,
                MapCodec<TeleportAttackerEffect>> TELEPORT_ATTACKER =
        ENTITY_EFFECTS.register("teleport_attacker",
            () -> TeleportAttackerEffect.CODEC);

    public static void init() {}
}