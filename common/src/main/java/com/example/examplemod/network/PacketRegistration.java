package com.example.examplemod.network;

import commonnetwork.api.Network;

public class PacketRegistration {
    public static void init() {
        Network.registerPacket(
                ExampleS2CPacket.type(),
                ExampleS2CPacket.class,
                ExampleS2CPacket.STREAM_CODEC,
                ExampleS2CPacket::handle);

        Network.registerPacket(
                ExampleActionC2SPacket.type(),
                ExampleActionC2SPacket.class,
                ExampleActionC2SPacket.STREAM_CODEC,
                ExampleActionC2SPacket::handle);
    }
}