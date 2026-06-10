package com.example.examplemod;


import com.example.examplemod.config.ExampleConfig;
import com.example.examplemod.config.NeoForgeExampleConfig;
import com.example.examplemod.data.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod(Constants.MOD_ID)
public class ExampleMod
{
    public ExampleMod(IEventBus eventBus) {
        ExampleConfig.setInstance(new NeoForgeExampleConfig());
        ModLoadingContext.get().getActiveContainer().registerConfig(ModConfig.Type.COMMON, NeoForgeExampleConfig.SPEC);
        CommonClass.init();
        eventBus.addListener(ExampleMod::gatherData);
    }

    public static void gatherData(GatherDataEvent event) {
        try {
            DataGenerator generator = event.getGenerator();
            PackOutput output = generator.getPackOutput();
            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
            CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();

            generator.addProvider(true,  new ExampleItemModelProvider(output, existingFileHelper));
            generator.addProvider(true, new ExampleBlockStateProvider(output, Constants.MOD_ID, existingFileHelper));
            generator.addProvider(true,
                    new LootTableProvider(output, Set.of(),
                            List.of(
                                    new LootTableProvider.SubProviderEntry(
                                            ExampleBlockLootTableProvider::new,
                                            LootContextParamSets.BLOCK),
                                    new LootTableProvider.SubProviderEntry(
                                            ExampleEntityLootProvider::new,
                                            LootContextParamSets.ENTITY)
                            ), registries));
            generator.addProvider(true,
                    new ExampleRecipeProvider(output, registries));

            ExampleBlockTagsProvider blockTagsProvider = generator.addProvider(true,
                    new ExampleBlockTagsProvider(output, registries, existingFileHelper));
            generator.addProvider(true,
                    new ExampleItemTagsProvider(output, registries,
                            blockTagsProvider.contentsGetter(), existingFileHelper));

            generator.addProvider(true,
                    new ExampleSoundDefinitionsProvider(output, existingFileHelper));

            generator.addProvider(
                    event.includeServer(),
                    new ExampleAdvancementProvider(output, registries, existingFileHelper));

            generator.addProvider(
                    event.includeClient(),
                    new ExampleLangProvider(output));

            generator.addProvider(true,
                    new DatapackBuiltinEntriesProvider(output, registries,
                            new RegistrySetBuilder()
                                    .add(Registries.CONFIGURED_FEATURE, ExampleWorldGenProvider::configuredFeatures)
                                    .add(Registries.PLACED_FEATURE,     ExampleWorldGenProvider::placedFeatures)
                                    .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ExampleWorldGenProvider::biomeModifiers),
                            Set.of(Constants.MOD_ID)));
        } catch (RuntimeException e) {
            Constants.LOG.error("Failed to generate data", e);
        }
    }
}