package com.example.examplemod.tag;

import com.example.examplemod.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ExampleTags {
    public static final TagKey<Block> INCORRECT_FOR_EXAMPLE_TOOL =
            TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "incorrect_for_example_tool"));
}