package com.example.examplemod.registry;

import com.example.examplemod.Constants;
import com.example.examplemod.registration.RegistrationProvider;
import com.example.examplemod.registration.RegistryObject;
import com.example.examplemod.util.ExampleToolTier;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;

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

    public static final RegistryObject<Item, SwordItem> EXAMPLE_SWORD = ITEMS.register("example_sword",
            () -> new SwordItem(ExampleToolTier.EXAMPLE, getItemProperties()));

    public static final RegistryObject<Item, PickaxeItem> EXAMPLE_PICKAXE = ITEMS.register("example_pickaxe",
            () -> new PickaxeItem(ExampleToolTier.EXAMPLE, getItemProperties()));

    public static final RegistryObject<Item, AxeItem> EXAMPLE_AXE = ITEMS.register("example_axe",
            () -> new AxeItem(ExampleToolTier.EXAMPLE, getItemProperties()));

    public static final RegistryObject<Item, ShovelItem> EXAMPLE_SHOVEL = ITEMS.register("example_shovel",
            () -> new ShovelItem(ExampleToolTier.EXAMPLE, getItemProperties()));

    public static final RegistryObject<Item, HoeItem> EXAMPLE_HOE = ITEMS.register("example_hoe",
            () -> new HoeItem(ExampleToolTier.EXAMPLE, getItemProperties()));

    public static final RegistryObject<Item, ArmorItem> EXAMPLE_HELMET =
            ITEMS.register("example_helmet", () -> new ArmorItem(
                    Holder.direct(ArmourMaterialRegistry.EXAMPLE.get()),
                    ArmorItem.Type.HELMET,
                    getItemProperties().stacksTo(1)));

    public static final RegistryObject<Item, ArmorItem> EXAMPLE_CHESTPLATE =
            ITEMS.register("example_chestplate", () -> new ArmorItem(
                    Holder.direct(ArmourMaterialRegistry.EXAMPLE.get()),
                    ArmorItem.Type.CHESTPLATE,
                    getItemProperties().stacksTo(1)));

    public static final RegistryObject<Item, ArmorItem> EXAMPLE_LEGGINGS =
            ITEMS.register("example_leggings", () -> new ArmorItem(
                    Holder.direct(ArmourMaterialRegistry.EXAMPLE.get()),
                    ArmorItem.Type.LEGGINGS,
                    getItemProperties().stacksTo(1)));

    public static final RegistryObject<Item, ArmorItem> EXAMPLE_BOOTS =
            ITEMS.register("example_boots", () -> new ArmorItem(
                    Holder.direct(ArmourMaterialRegistry.EXAMPLE.get()),
                    ArmorItem.Type.BOOTS,
                    getItemProperties().stacksTo(1)));

    public static final RegistryObject<Item, Item> EXAMPLE_ENTITY_SPAWN_EGG =
            ITEMS.register("example_entity_spawn_egg", () ->
                    new SpawnEggItem(EntityRegistry.EXAMPLE_ENTITY.get(), 0x8B4513, 0x228B22,
                            getItemProperties()));

    public static Item.Properties getItemProperties() {
        return new Item.Properties();
    }
}