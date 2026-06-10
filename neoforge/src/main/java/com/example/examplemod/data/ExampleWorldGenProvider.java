package com.example.examplemod.data;

import com.example.examplemod.Constants;
import com.example.examplemod.registry.BlockRegistry;
import com.example.examplemod.world.ExampleWorldGen;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;

public class ExampleWorldGenProvider
{
    public static void configuredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        context.register(ExampleWorldGen.NEW_DIRT_ORE_CONFIGURED,
                new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(
                        List.of(
                                OreConfiguration.target(stoneReplaceables,
                                        BlockRegistry.NEW_DIRT.get().defaultBlockState()),
                                OreConfiguration.target(deepslateReplaceables,
                                        BlockRegistry.NEW_DIRT.get().defaultBlockState())
                        ),
                        9   // vein size
                )));
    }

    public static void placedFeatures(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configured =
                context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(ExampleWorldGen.NEW_DIRT_ORE_PLACED,
                new PlacedFeature(
                        configured.getOrThrow(ExampleWorldGen.NEW_DIRT_ORE_CONFIGURED),
                        List.of(
                                CountPlacement.of(4),           // veins per chunk
                                InSquarePlacement.spread(),      // scatter across the chunk column
                                HeightRangePlacement.triangle(
                                        VerticalAnchor.absolute(-80),
                                        VerticalAnchor.absolute(80)),
                                BiomeFilter.biome()             // skip chunks whose biome doesn't match
                        )
                ));
    }

    public static void biomeModifiers(BootstrapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placed   = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome>         biomes   = context.lookup(Registries.BIOME);

        context.register(
                ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,
                        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "add_new_dirt_ore")),
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(placed.getOrThrow(ExampleWorldGen.NEW_DIRT_ORE_PLACED)),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                ));
    }
}
