package com.example.examplemod;

import com.example.examplemod.config.ExampleConfig;
import com.example.examplemod.config.FabricExampleConfig;
import com.example.examplemod.entity.ExampleEntity;
import com.example.examplemod.event.CommonEvents;
import com.example.examplemod.registry.EntityRegistry;
import com.example.examplemod.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTabs;
import org.spongepowered.asm.mixin.MixinEnvironment;

public class ExampleMod implements ModInitializer
{
    @Override
    public void onInitialize() {
        ExampleConfig.setInstance(FabricExampleConfig.load());
        CommonClass.init();
        registerEvents();
        FabricDefaultAttributeRegistry.register(
                EntityRegistry.EXAMPLE_ENTITY.get(), ExampleEntity.createAttributes());

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register(entries ->
                entries.accept(ItemRegistry.EXAMPLE_ENTITY_SPAWN_EGG.get()));
    }
    private static void registerEvents() {
        // Player login
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register((world, entity, killedEntity) -> {});
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {});
        // Player login event
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            if (handler.getPlayer() instanceof ServerPlayer player) {
                CommonEvents.onPlayerLogin(player);
            }
        });
        // Block break event
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            CommonEvents.onBlockBreak(world, pos, state, player);
        });
    }
}
