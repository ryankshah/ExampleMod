package com.example.examplemod.event;

import com.example.examplemod.Constants;
import com.example.examplemod.entity.ExampleEntity;
import com.example.examplemod.registry.EntityRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ExampleEvents {
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            CommonEvents.onPlayerLogin(player);
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        CommonEvents.onBlockBreak(
            event.getLevel() instanceof Level level ? level : null,
            event.getPos(),
            event.getState(),
            event.getPlayer()
        );
    }

    @SubscribeEvent
    public static void onDamageEvent(LivingIncomingDamageEvent event) {
        CommonEvents.onLivingHurt(
                event.getEntity(),
                event.getSource(),
                event.getAmount()
        );
    }
}