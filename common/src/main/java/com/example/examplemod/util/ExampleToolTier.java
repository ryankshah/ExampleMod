package com.example.examplemod.util;

import com.example.examplemod.registry.ItemRegistry;
import com.example.examplemod.tag.ExampleTags;
import com.google.common.base.Suppliers;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public enum ExampleToolTier implements Tier {

    EXAMPLE(
        ExampleTags.INCORRECT_FOR_EXAMPLE_TOOL,
        500,
        6.5f,
        2.0f,
        14,
        () -> Ingredient.of(ItemRegistry.IRON_STICK.get())
    );

    private final TagKey<Block> incorrectBlocksForDrops;
    private final int uses;
    private final float speed;
    private final float attackDamageBonus;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    ExampleToolTier(TagKey<Block> incorrectBlocksForDrops, int uses, float speed,
            float attackDamageBonus, int enchantmentValue,
            Supplier<Ingredient> repairIngredient) {
        this.incorrectBlocksForDrops = incorrectBlocksForDrops;
        this.uses = uses;
        this.speed = speed;
        this.attackDamageBonus = attackDamageBonus;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    @Override public int getUses()                        { return uses; }
    @Override public float getSpeed()                    { return speed; }
    @Override public float getAttackDamageBonus()        { return attackDamageBonus; }
    @Override public TagKey<Block> getIncorrectBlocksForDrops() { return incorrectBlocksForDrops; }
    @Override public int getEnchantmentValue()           { return enchantmentValue; }
    @Override public Ingredient getRepairIngredient()    { return repairIngredient.get(); }
}