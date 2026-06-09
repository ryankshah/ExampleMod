package com.example.examplemod.registry;

import com.example.examplemod.Constants;
import com.example.examplemod.blockentity.ExampleBlockEntity;
import com.example.examplemod.registration.RegistrationProvider;
import com.example.examplemod.registration.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BlockEntityRegistry {

    public static final RegistrationProvider<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            RegistrationProvider.get(Registries.BLOCK_ENTITY_TYPE, Constants.MOD_ID);

    public static final RegistryObject<BlockEntityType<?>, BlockEntityType<ExampleBlockEntity>> EXAMPLE =
            BLOCK_ENTITY_TYPES.register("example", () ->
                    BlockEntityType.Builder.of(ExampleBlockEntity::new, BlockRegistry.EXAMPLE_BE_BLOCK.get())
                            .build(null));

    public static void init() {}
}