package com.example.examplemod.registry;

import com.example.examplemod.Constants;
import com.example.examplemod.menu.ExampleMenu;
import com.example.examplemod.registration.RegistrationProvider;
import com.example.examplemod.registration.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public class MenuRegistry {

    public static final RegistrationProvider<MenuType<?>> MENU_TYPES =
            RegistrationProvider.get(Registries.MENU, Constants.MOD_ID);

    public static final RegistryObject<MenuType<?>, MenuType<ExampleMenu>> EXAMPLE_MENU =
            MENU_TYPES.register("example_menu",
                () -> new MenuType<>(ExampleMenu::new, FeatureFlags.DEFAULT_FLAGS));

    public static void init() {}
}