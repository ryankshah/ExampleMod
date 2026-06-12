package com.example.examplemod.registry;

import com.example.examplemod.Constants;
import com.example.examplemod.registration.RegistrationProvider;
import com.example.examplemod.registration.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

public class PotionRegistry {

    public static final RegistrationProvider<Potion> POTIONS =
            RegistrationProvider.get(Registries.POTION, Constants.MOD_ID);

    public static final RegistryObject<Potion, Potion> EXAMPLE_POTION =
            POTIONS.register("example",
                () -> new Potion(
                    new MobEffectInstance(EffectRegistry.EXAMPLE_EFFECT.asHolder(), 900, 0)));

    public static final RegistryObject<Potion, Potion> EXAMPLE_POTION_STRONG =
            POTIONS.register("strong_example",
                () -> new Potion(
                    new MobEffectInstance(EffectRegistry.EXAMPLE_EFFECT.asHolder(), 450, 1)));

    public static void init() {}
}