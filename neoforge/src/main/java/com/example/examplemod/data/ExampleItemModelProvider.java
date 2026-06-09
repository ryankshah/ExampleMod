package com.example.examplemod.data;

import com.example.examplemod.Constants;
import com.example.examplemod.registry.ItemRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Objects;

public class ExampleItemModelProvider extends ItemModelProvider
{
    public ExampleItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Constants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ItemRegistry.IRON_STICK.get());
        basicItem(ItemRegistry.TOMATO.get());
        handheldItem(ItemRegistry.EXAMPLE_SWORD.get());
        handheldItem(ItemRegistry.EXAMPLE_PICKAXE.get());
        handheldItem(ItemRegistry.EXAMPLE_AXE.get());
        handheldItem(ItemRegistry.EXAMPLE_SHOVEL.get());
        handheldItem(ItemRegistry.EXAMPLE_HOE.get());
        basicItem(ItemRegistry.EXAMPLE_HELMET.get());
        basicItem(ItemRegistry.EXAMPLE_CHESTPLATE.get());
        basicItem(ItemRegistry.EXAMPLE_LEGGINGS.get());
        basicItem(ItemRegistry.EXAMPLE_BOOTS.get());
    }

    public ItemModelBuilder handheldItem(Item item) {
        ResourceLocation itemRL = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item));
        return this.getBuilder(itemRL.toString()).parent(new ModelFile.UncheckedModelFile("item/handheld")).texture("layer0", ResourceLocation.fromNamespaceAndPath(itemRL.getNamespace(), "item/" + itemRL.getPath()));
    }
}