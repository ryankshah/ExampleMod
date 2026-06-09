package com.example.examplemod.blockentity;

import com.example.examplemod.registry.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class ExampleBlockEntity extends BlockEntity {

    private int secondsAlive = 0;

    public ExampleBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.EXAMPLE.get(), pos, state);
    }

    public int getSecondsAlive() {
        return secondsAlive;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, ExampleBlockEntity entity) {
        if (level.isClientSide()) return;
        if (level.getGameTime() % 20 == 0) {
            entity.secondsAlive++;
            entity.setChanged();
            level.sendBlockUpdated(pos, state, state, Block.UPDATE_CLIENTS);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("SecondsAlive", secondsAlive);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        secondsAlive = tag.getInt("SecondsAlive");
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    @Nullable
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}