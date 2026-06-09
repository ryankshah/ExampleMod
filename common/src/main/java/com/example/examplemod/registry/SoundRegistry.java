package com.example.examplemod.registry;

import com.example.examplemod.Constants;
import com.example.examplemod.registration.RegistrationProvider;
import com.example.examplemod.registration.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class SoundRegistry {

    public static final RegistrationProvider<SoundEvent> SOUNDS =
            RegistrationProvider.get(Registries.SOUND_EVENT, Constants.MOD_ID);

    public static final RegistryObject<SoundEvent, SoundEvent> EXAMPLE_AMBIENT =
            SOUNDS.register("example_ambient", () ->
                SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "example_ambient")));

    public static void init() {}
}