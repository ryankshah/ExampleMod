package com.example.examplemod.registry;

import com.example.examplemod.Constants;
import com.example.examplemod.registration.RegistrationProvider;
import com.example.examplemod.registration.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreativeTabRegistry
{
    public static void init() {}

    public static final RegistrationProvider<CreativeModeTab> CREATIVE_MODE_TABS = RegistrationProvider.get(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);

    public static final RegistryObject<CreativeModeTab, CreativeModeTab> ITEMS_TAB = CREATIVE_MODE_TABS.register(Constants.MOD_ID + "_items_tab", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .icon(() -> new ItemStack(ItemRegistry.IRON_STICK.get()))
            .displayItems(
                    (itemDisplayParameters, output) -> {
                        output.accept(ItemRegistry.IRON_STICK.get());
                    }).title(Component.translatable("itemGroup." + Constants.MOD_ID + ".items"))
            .build());

    public static final RegistryObject<CreativeModeTab, CreativeModeTab> BLOCKS_TAB = CREATIVE_MODE_TABS.register(Constants.MOD_ID + "_blocks_tab", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .icon(() -> new ItemStack(BlockRegistry.NEW_DIRT.get()))
            .displayItems(
                    (itemDisplayParameters, output) -> {
                        output.accept(BlockRegistry.NEW_DIRT.get());
                    }).title(Component.translatable("itemGroup." + Constants.MOD_ID + ".blocks"))
            .build());
}