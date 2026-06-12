package com.example.examplemod.client;

import com.example.examplemod.network.ExampleActionC2SPacket;
import com.mojang.blaze3d.platform.InputConstants;
import commonnetwork.api.Dispatcher;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public class ExampleKeyBindings {
    public static KeyMapping EXAMPLE_ACTION;
    public static KeyMapping createExampleAction() {
        return new KeyMapping(
            "key.examplemod.example_action",  // translation key
            InputConstants.Type.KEYSYM,        // keyboard (not mouse)
            GLFW.GLFW_KEY_G,                   // default key: G
            "key.categories.examplemod"        // category translation key
        );
    }

    public static void onClientTick() {
        if (EXAMPLE_ACTION == null) return;

        while (EXAMPLE_ACTION.consumeClick()) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null || mc.screen != null) continue;

            // Case 1: Client-only action (display a message)
            mc.player.sendSystemMessage(Component.literal("Key pressed!"));

            // Case 2: Open a client-side screen
            // mc.setScreen(new MyCustomScreen());

            // Case 3: Send a C2S packet to trigger something on the server
            if (Minecraft.getInstance().player != null) {
                Dispatcher.sendToServer(new ExampleActionC2SPacket());
            }
        }
    }
}