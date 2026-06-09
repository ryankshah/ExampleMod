package com.example.examplemod.data;

import com.example.examplemod.Constants;
import com.example.examplemod.registry.BlockRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ExampleBlockTagsProvider extends BlockTagsProvider {

    public ExampleBlockTagsProvider(PackOutput output,
                                    CompletableFuture<HolderLookup.Provider> lookupProvider,
                                    ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Constants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BlockRegistry.NEW_DIRT.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(BlockRegistry.NEW_DIRT.get());
    }
}