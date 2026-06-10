package com.example.examplemod.client;

import com.example.examplemod.Constants;
import com.example.examplemod.entity.ExampleEntity;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ExampleEntityRenderer extends MobRenderer<ExampleEntity, PigModel<ExampleEntity>> {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/example_entity.png");

    public ExampleEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new PigModel<>(context.bakeLayer(ModelLayers.PIG)), 0.7f);
    }

    @Override
    public ResourceLocation getTextureLocation(ExampleEntity entity) {
        return TEXTURE;
    }
}