package com.example.examplemod.world;

import com.example.examplemod.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ExampleWorldGen {

    public static final ResourceKey<ConfiguredFeature<?, ?>> NEW_DIRT_ORE_CONFIGURED =
            ResourceKey.create(Registries.CONFIGURED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "new_dirt_ore"));

    public static final ResourceKey<PlacedFeature> NEW_DIRT_ORE_PLACED =
            ResourceKey.create(Registries.PLACED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "new_dirt_ore"));
}