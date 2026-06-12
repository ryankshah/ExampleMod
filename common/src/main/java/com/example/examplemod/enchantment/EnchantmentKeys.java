package com.example.examplemod.enchantment;

import com.example.examplemod.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

public class EnchantmentKeys {

    public static final ResourceKey<Enchantment> HEALING_STRIKE =
        ResourceKey.create(
            Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "healing_strike")
        );
}