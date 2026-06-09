package com.example.examplemod.data;

import com.example.examplemod.Constants;
import com.example.examplemod.registry.BlockRegistry;
import com.example.examplemod.tag.ExampleTags;
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

        // Custom tier tag: extends the iron incorrect set so example-tier
        // tools can harvest iron-tier blocks, plus any blocks specific to
        // your tier that stone-tier tools cannot harvest
        tag(ExampleTags.INCORRECT_FOR_EXAMPLE_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL);
    }
}