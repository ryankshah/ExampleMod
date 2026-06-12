package com.example.examplemod.data;

import com.example.examplemod.enchantment.EnchantmentKeys;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.ApplyMobEffect;

public class ExampleEnchantmentBootstrap {

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Item> items = context.lookup(Registries.ITEM);
        HolderGetter<MobEffect> effects = context.lookup(Registries.MOB_EFFECT);

        context.register(
            EnchantmentKeys.HEALING_STRIKE,
            Enchantment.enchantment(
                Enchantment.definition(
                    items.getOrThrow(ItemTags.SWORD_ENCHANTABLE), // supported items
                    items.getOrThrow(ItemTags.SWORD_ENCHANTABLE), // primary items
                    3,    // weight
                    3,    // max level
                    Enchantment.dynamicCost(10, 8),  // min cost
                    Enchantment.dynamicCost(30, 8),  // max cost
                    4,    // anvil cost
                    EquipmentSlotGroup.MAINHAND
                )
            )
            .withEffect(
                EnchantmentEffectComponents.POST_ATTACK,
                EnchantmentTarget.ATTACKER,
                EnchantmentTarget.VICTIM,
                new ApplyMobEffect(
                    HolderSet.direct(MobEffects.REGENERATION),
                    LevelBasedValue.perLevel(2.0f, 1.0f),  // min duration
                    LevelBasedValue.perLevel(2.0f, 1.0f),  // max duration
                    LevelBasedValue.constant(0),            // min amplifier
                    LevelBasedValue.constant(0)             // max amplifier
                )
            )
            .build(EnchantmentKeys.HEALING_STRIKE.location())
        );
    }
}