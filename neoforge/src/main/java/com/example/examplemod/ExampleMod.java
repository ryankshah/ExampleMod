package com.example.examplemod;


import com.example.examplemod.data.ExampleBlockStateProvider;
import com.example.examplemod.data.ExampleItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

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

            generator.addProvider(true,  new ExampleItemModelProvider(output, existingFileHelper));
            generator.addProvider(true, new ExampleBlockStateProvider(output, Constants.MOD_ID, existingFileHelper));
        } catch (RuntimeException e) {
            Constants.LOG.error("Failed to generate data", e);
        }
    }
}