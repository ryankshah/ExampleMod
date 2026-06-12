package com.example.examplemod.registry;

import com.example.examplemod.Constants;
import com.example.examplemod.effect.ExampleEffect;
import com.example.examplemod.registration.RegistrationProvider;
import com.example.examplemod.registration.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;

public class EffectRegistry {

    public static final RegistrationProvider<MobEffect> MOB_EFFECTS =
            RegistrationProvider.get(Registries.MOB_EFFECT, Constants.MOD_ID);

    public static final RegistryObject<MobEffect, ExampleEffect> EXAMPLE_EFFECT =
            MOB_EFFECTS.register("example_effect", ExampleEffect::new);

    public static void init() {}
}