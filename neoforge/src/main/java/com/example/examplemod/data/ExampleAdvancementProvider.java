package com.example.examplemod.data;

import com.example.examplemod.Constants;
import com.example.examplemod.registry.BlockRegistry;
import com.example.examplemod.registry.ItemRegistry;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.ImpossibleTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemUsedOnLocationTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ExampleAdvancementProvider extends AdvancementProvider {

    public ExampleAdvancementProvider(PackOutput output,
                                      CompletableFuture<HolderLookup.Provider> lookupProvider,
                                      ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, existingFileHelper,
                List.of(new ExampleAdvancementGenerator()));
    }

    private static final class ExampleAdvancementGenerator
            implements AdvancementProvider.AdvancementGenerator {

        @Override
        public void generate(HolderLookup.Provider registries,
                             Consumer<AdvancementHolder> saver,
                             ExistingFileHelper existingFileHelper) {
            AdvancementHolder root = Advancement.Builder.advancement()
                    .display(
                            ItemRegistry.IRON_STICK.get(),
                            Component.translatable("advancements.examplemod.root.title"),
                            Component.translatable("advancements.examplemod.root.description"),
                            ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/advancements/backgrounds/stone.png"),
                            AdvancementType.TASK,
                            false,  // show toast
                            false,  // announce to chat
                            false   // hidden
                    )
                    .addCriterion("impossible", CriteriaTriggers.IMPOSSIBLE.createCriterion(
                            new ImpossibleTrigger.TriggerInstance()))
                    .save(saver, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "root"), existingFileHelper);

            AdvancementHolder getIronStick = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            ItemRegistry.IRON_STICK.get(),
                            Component.translatable("advancements.examplemod.get_iron_stick.title"),
                            Component.translatable("advancements.examplemod.get_iron_stick.description"),
                            null,   // null background for non-root nodes
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("has_iron_stick", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemRegistry.IRON_STICK.get()))
                    .save(saver, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "get_iron_stick"), existingFileHelper);

            AdvancementHolder placeNewDirt = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            BlockRegistry.NEW_DIRT.get().asItem(),
                            Component.translatable("advancements.examplemod.place_new_dirt.title"),
                            Component.translatable("advancements.examplemod.place_new_dirt.description"),
                            null,
                            AdvancementType.GOAL,
                            true,
                            true,
                            false
                    )
                    .addCriterion("placed_new_dirt", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(
                            BlockRegistry.NEW_DIRT.get()))
                    .save(saver, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "place_new_dirt"), existingFileHelper);
        }
    }
}