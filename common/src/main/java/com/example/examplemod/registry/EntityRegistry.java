package com.example.examplemod.registry;

import com.example.examplemod.Constants;
import com.example.examplemod.entity.ExampleEntity;
import com.example.examplemod.registration.RegistrationProvider;
import com.example.examplemod.registration.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class EntityRegistry {

    public static final RegistrationProvider<EntityType<?>> ENTITY_TYPES =
            RegistrationProvider.get(Registries.ENTITY_TYPE, Constants.MOD_ID);

    public static final RegistryObject<EntityType<?>, EntityType<ExampleEntity>> EXAMPLE_ENTITY =
            ENTITY_TYPES.register("example_entity", () ->
                EntityType.Builder.<ExampleEntity>of(ExampleEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.8f)
                    .clientTrackingRange(8)
                    .build(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "example_entity").toString()));

    public static void init() {}
}