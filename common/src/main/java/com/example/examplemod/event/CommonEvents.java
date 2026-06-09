package com.example.examplemod.event;

import com.example.examplemod.Constants;
import com.example.examplemod.registry.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class CommonEvents {

    public static void onPlayerLogin(ServerPlayer player) {
        player.sendSystemMessage(
            Component.literal("Welcome, " + player.getName().getString() + "!"));
    }

    public static void onBlockBreak(Level level, BlockPos pos, BlockState state, Player player) {
        if (state.is(BlockRegistry.NEW_DIRT.get())) {
            Constants.LOG.info("{} broke a New Dirt block at {}", player.getName().getString(), pos);
        }
    }

    public static boolean onLivingHurt(LivingEntity entity, DamageSource source, float amount) {
        if (entity.level().isClientSide()) return true;
        if (source.is(DamageTypes.FELL_OUT_OF_WORLD)) {
            Constants.LOG.info("{} fell out of the world", entity.getName().getString());
        }
        return true;
    }
}