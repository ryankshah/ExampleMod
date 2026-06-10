package com.example.examplemod.event;

import com.example.examplemod.Constants;
import com.example.examplemod.entity.ExampleEntity;
import com.example.examplemod.registry.EntityRegistry;
import com.example.examplemod.registry.ItemRegistry;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ExampleModEvents
{
    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.EXAMPLE_ENTITY.get(), ExampleEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void onBuildCreativeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ItemRegistry.EXAMPLE_ENTITY_SPAWN_EGG.get());
        }
    }
}
