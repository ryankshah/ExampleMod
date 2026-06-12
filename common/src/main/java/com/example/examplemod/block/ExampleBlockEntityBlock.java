package com.example.examplemod.block;

import com.example.examplemod.blockentity.ExampleBlockEntity;
import com.example.examplemod.network.ExampleS2CPacket;
import com.example.examplemod.registry.BlockEntityRegistry;
import com.example.examplemod.registry.ParticleRegistry;
import com.mojang.serialization.MapCodec;
import commonnetwork.api.Dispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class ExampleBlockEntityBlock extends BaseEntityBlock {

    public static final MapCodec<ExampleBlockEntityBlock> CODEC =
            simpleCodec(ExampleBlockEntityBlock::new);

    public ExampleBlockEntityBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ExampleBlockEntity(pos, state);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos,
                         BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock())) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof Container container) {
                Containers.dropContents(level, pos, container);
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        if (!pLevel.isClientSide() && pPlayer instanceof ServerPlayer serverPlayer) {
            BlockEntity be = pLevel.getBlockEntity(pPos);
            Dispatcher.sendToClient(new ExampleS2CPacket(42, "hello"), serverPlayer);
            if (pLevel instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        ParticleRegistry.SPARKLE.get(),
                        pPos.getX() + 0.5, pPos.getY() + 1.0, pPos.getZ() + 0.5,
                        15,      // count
                        0.3,     // spread X
                        0.2,     // spread Y
                        0.3,     // spread Z
                        0.05     // speed
                );
            }
            if (be instanceof MenuProvider menuProvider) {
                serverPlayer.openMenu(menuProvider);
            }
        }
        return super.useWithoutItem(pState, pLevel, pPos, pPlayer, pHitResult);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, BlockEntityRegistry.EXAMPLE.get(), ExampleBlockEntity::tick);
    }
}