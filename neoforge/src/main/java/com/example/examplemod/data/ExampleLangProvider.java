package com.example.examplemod.data;

import com.example.examplemod.Constants;
import com.example.examplemod.registry.BlockRegistry;
import com.example.examplemod.registry.EntityRegistry;
import com.example.examplemod.registry.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ExampleLangProvider extends LanguageProvider {

    public ExampleLangProvider(PackOutput output) {
        super(output, Constants.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addItem(ItemRegistry.IRON_STICK,       "Iron Stick");
        addItem(ItemRegistry.TOMATO,           "Tomato");
        addItem(ItemRegistry.EXAMPLE_SWORD,    "Example Sword");
        addItem(ItemRegistry.EXAMPLE_PICKAXE,  "Example Pickaxe");
        addItem(ItemRegistry.EXAMPLE_AXE,      "Example Axe");
        addItem(ItemRegistry.EXAMPLE_SHOVEL,   "Example Shovel");
        addItem(ItemRegistry.EXAMPLE_HOE,      "Example Hoe");
        addItem(ItemRegistry.EXAMPLE_HELMET,   "Example Helmet");
        addItem(ItemRegistry.EXAMPLE_CHESTPLATE, "Example Chestplate");
        addItem(ItemRegistry.EXAMPLE_LEGGINGS, "Example Leggings");
        addItem(ItemRegistry.EXAMPLE_BOOTS,    "Example Boots");

        addBlock(BlockRegistry.NEW_DIRT,        "New Dirt");
        addBlock(BlockRegistry.EXAMPLE_BE_BLOCK, "Example Block Entity Block");

        add("itemGroup.examplemod.items",  "Example Mod Items");
        add("itemGroup.examplemod.blocks", "Example Mod Blocks");

        add("subtitles.examplemod.example_ambient", "Example ambient sound");

        add("advancements.examplemod.root.title",               "Example Mod");
        add("advancements.examplemod.root.description",         "Begin your journey with Example Mod.");
        add("advancements.examplemod.get_iron_stick.title",     "A New Tool");
        add("advancements.examplemod.get_iron_stick.description", "Pick up an Iron Stick.");
        add("advancements.examplemod.place_new_dirt.title",     "Breaking Ground");
        add("advancements.examplemod.place_new_dirt.description", "Place a New Dirt block.");

        add("container.examplemod.example", "Example Container");

        add("key.examplemod.example_action", "Example Action");
        add("key.categories.examplemod", "Example Mod");

        add("effect.examplemod.example_effect", "Vitality");

        add("item.minecraft.potion.effect.example", "Potion of Vitality");
        add("item.minecraft.splash_potion.effect.example", "Splash Potion of Vitality");
        add("item.minecraft.lingering_potion.effect.example", "Lingering Potion of Vitality");
        add("item.minecraft.tipped_arrow.effect.example", "Arrow of Vitality");

        add("item.minecraft.potion.effect.strong_example", "Potion of Vitality II");
        add("item.minecraft.splash_potion.effect.strong_example", "Splash Potion of Vitality II");
        add("item.minecraft.lingering_potion.effect.strong_example", "Lingering Potion of Vitality II");
        add("item.minecraft.tipped_arrow.effect.strong_example", "Arrow of Vitality II");

        add(EntityRegistry.EXAMPLE_ENTITY.get(), "Example Entity");
        add(ItemRegistry.EXAMPLE_ENTITY_SPAWN_EGG.get(), "Example Entity Spawn Egg");
    }
}