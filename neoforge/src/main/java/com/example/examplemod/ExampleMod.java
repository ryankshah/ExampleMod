package com.example.examplemod;


import com.example.examplemod.data.ExampleBlockLootTableProvider;
import com.example.examplemod.data.ExampleBlockStateProvider;
import com.example.examplemod.data.ExampleItemModelProvider;
import com.example.examplemod.data.ExampleRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod(Constants.MOD_ID)
public class ExampleMod
{
    public ExampleMod(IEventBus eventBus) {
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
                            List.of(new LootTableProvider.SubProviderEntry(
                                    ExampleBlockLootTableProvider::new,
                                    LootContextParamSets.BLOCK
                            )), registries));
            generator.addProvider(true,
                    new ExampleRecipeProvider(output, registries));
        } catch (RuntimeException e) {
            Constants.LOG.error("Failed to generate data", e);
        }
    }
}