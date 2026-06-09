package com.example.examplemod.data;

import com.example.examplemod.Constants;
import com.example.examplemod.registry.BlockRegistry;
import com.example.examplemod.registry.ItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ExampleItemTagsProvider extends ItemTagsProvider {

    public ExampleItemTagsProvider(PackOutput output,
                                   CompletableFuture<HolderLookup.Provider> lookupProvider,
                                   CompletableFuture<TagLookup<Block>> blockTags,
                                   ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Constants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        TagKey<Item> modItems = ItemTags.create(
                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "mod_items"));

        tag(modItems)
                .add(ItemRegistry.IRON_STICK.get())
                .add(BlockRegistry.NEW_DIRT.get().asItem());
    }
}