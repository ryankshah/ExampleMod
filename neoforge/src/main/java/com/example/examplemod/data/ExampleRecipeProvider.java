package com.example.examplemod.data;

import com.example.examplemod.Constants;
import com.example.examplemod.registry.BlockRegistry;
import com.example.examplemod.registry.ItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class ExampleRecipeProvider extends RecipeProvider {

    public ExampleRecipeProvider(PackOutput output,
                                 CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.IRON_STICK.get(), 4)
                .pattern("I")
                .pattern("I")
                .define('I', Items.IRON_INGOT)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.NEW_DIRT.get())
                .requires(Items.DIRT)
                .requires(ItemRegistry.IRON_STICK.get())
                .unlockedBy("has_iron_stick", has(ItemRegistry.IRON_STICK.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "new_dirt_from_stick"));

        // Smelting (furnace only)
        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(BlockRegistry.NEW_DIRT.get()),
                        RecipeCategory.BUILDING_BLOCKS,
                        Items.COARSE_DIRT,
                        0.1f,
                        200)
                .unlockedBy("has_new_dirt", has(BlockRegistry.NEW_DIRT.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "coarse_dirt_from_smelting"));

// Blasting (blast furnace, twice as fast)
        SimpleCookingRecipeBuilder.blasting(
                        Ingredient.of(BlockRegistry.NEW_DIRT.get()),
                        RecipeCategory.BUILDING_BLOCKS,
                        Items.COARSE_DIRT,
                        0.1f,
                        100)
                .unlockedBy("has_new_dirt", has(BlockRegistry.NEW_DIRT.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "coarse_dirt_from_blasting"));
    }
}