package com.example.examplemod.registry;

import com.example.examplemod.Constants;
import com.example.examplemod.registration.RegistrationProvider;
import com.example.examplemod.registration.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemRegistry
{
    public static void init() {}

    public static final FoodProperties TOMATO_PROPERTIES = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.3f)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0), 1.0f)
            .effect(new MobEffectInstance(MobEffects.POISON, 60, 0), 0.1f)
            .build();

    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, Constants.MOD_ID);

    public static final RegistryObject<Item, Item> IRON_STICK = ITEMS.register("iron_stick",
            () -> new Item(getItemProperties()));

    public static final RegistryObject<Item, Item> TOMATO = ITEMS.register("tomato",
            () -> new Item(getItemProperties().food(TOMATO_PROPERTIES)));

    public static Item.Properties getItemProperties() {
        return new Item.Properties();
    }
}