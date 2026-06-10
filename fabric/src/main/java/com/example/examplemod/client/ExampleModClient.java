package com.example.examplemod.client;

import com.example.examplemod.registry.EntityRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ExampleModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EntityRegistry.EXAMPLE_ENTITY.get(),
                ExampleEntityRenderer::new);
    }
}
