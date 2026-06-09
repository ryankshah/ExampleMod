package com.example.examplemod.network;

import com.example.examplemod.Constants;
import commonnetwork.networking.data.PacketContext;
import commonnetwork.networking.data.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public class ExampleS2CPacket {

    public static final ResourceLocation CHANNEL =
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "example_s2c");

    public static final StreamCodec<FriendlyByteBuf, ExampleS2CPacket> STREAM_CODEC =
        StreamCodec.ofMember(ExampleS2CPacket::encode, ExampleS2CPacket::new);

    private final int value;
    private final String message;

    public ExampleS2CPacket(int value, String message) {
        this.value = value;
        this.message = message;
    }

    // Decode constructor — called by StreamCodec when reading from the wire
    public ExampleS2CPacket(FriendlyByteBuf buf) {
        this.value = buf.readInt();
        this.message = buf.readUtf();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(value);
        buf.writeUtf(message);
    }

    public static CustomPacketPayload.Type<CustomPacketPayload> type() {
        return new CustomPacketPayload.Type<>(CHANNEL);
    }

    public static void handle(PacketContext<ExampleS2CPacket> ctx) {
        if (Side.CLIENT.equals(ctx.side())) {
            Minecraft.getInstance().execute(() -> {
                if (Minecraft.getInstance().player != null) {
                    Minecraft.getInstance().player.sendSystemMessage(
                        Component.literal("[S2C] value=" + ctx.message().value
                            + " msg=" + ctx.message().message));
                }
            });
        }
    }
}