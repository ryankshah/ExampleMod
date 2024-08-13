package com.example.examplemod;

import com.example.examplemod.platform.Services;
import com.example.examplemod.registry.BlockRegistry;
import com.example.examplemod.registry.CreativeTabRegistry;
import com.example.examplemod.registry.ItemRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;

public class CommonClass
{
    public static void init() {
        ItemRegistry.init();
        BlockRegistry.init();
        CreativeTabRegistry.init();
    }
}