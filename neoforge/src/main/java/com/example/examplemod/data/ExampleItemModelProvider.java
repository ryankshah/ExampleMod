package com.example.examplemod.data;

import com.example.examplemod.Constants;
import com.example.examplemod.registry.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ExampleItemModelProvider extends ItemModelProvider
{
    public ExampleItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Constants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ItemRegistry.IRON_STICK.get());
    }
}