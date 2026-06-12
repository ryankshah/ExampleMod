package com.example.examplemod.network;

import com.example.examplemod.Constants;
import commonnetwork.networking.data.PacketContext;
import commonnetwork.networking.data.Side;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class ExampleActionC2SPacket {

    public static final ResourceLocation CHANNEL =
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "example_action_c2s");

    public static final StreamCodec<FriendlyByteBuf, ExampleActionC2SPacket> STREAM_CODEC =
        StreamCodec.ofMember((p, buf) -> {}, buf -> new ExampleActionC2SPacket());

    public static CustomPacketPayload.Type<CustomPacketPayload> type() {
        return new CustomPacketPayload.Type<>(CHANNEL);
    }

    public static void handle(PacketContext<ExampleActionC2SPacket> ctx) {
        if (Side.SERVER.equals(ctx.side()) && ctx.sender() != null) {
            ServerPlayer player = ctx.sender();
            // Do server-side work here
            player.server.execute(() -> {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0));
            });
        }
    }
}