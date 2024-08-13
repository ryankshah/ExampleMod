package com.example.examplemod.registry;

import com.example.examplemod.Constants;
import com.example.examplemod.registration.RegistrationProvider;
import com.example.examplemod.registration.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemRegistry
{
    public static void init() {}

    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, Constants.MOD_ID);

    public static final RegistryObject<Item, Item> IRON_STICK = ITEMS.register("iron_stick",
            () -> new Item(getItemProperties()));

    public static Item.Properties getItemProperties() {
        return new Item.Properties();
    }


    public static final RegistrationProvider<CreativeModeTab> CREATIVE_MODE_TABS = RegistrationProvider.get(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);
    public static final RegistryObject<CreativeModeTab, CreativeModeTab> TAB = CREATIVE_MODE_TABS.register(Constants.MOD_ID + "_tab", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .icon(() -> new ItemStack(IRON_STICK.get()))
            .displayItems(
                    (itemDisplayParameters, output) -> {
                        output.accept(IRON_STICK.get());
                    }).title(Component.translatable("itemGroup." + Constants.MOD_ID + ".tab"))
            .build());


}