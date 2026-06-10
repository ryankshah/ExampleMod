package com.example.examplemod.data;

import com.example.examplemod.registration.RegistryObject;
import com.example.examplemod.registry.EntityRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExampleEntityLootProvider extends EntityLootSubProvider {

    public ExampleEntityLootProvider(HolderLookup.Provider registries) {
        super(FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    public void generate() {
        add(EntityRegistry.EXAMPLE_ENTITY.get(),
            LootTable.lootTable()
                .withPool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(Items.STRING)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                    .when(LootItemKilledByPlayerCondition.killedByPlayer())));
    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return EntityRegistry.ENTITY_TYPES.getEntries().stream()
                .map(RegistryObject::get);
    }
}