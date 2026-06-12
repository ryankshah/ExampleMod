package com.example.examplemod;

import com.example.examplemod.network.PacketRegistration;
import com.example.examplemod.platform.Services;
import com.example.examplemod.registry.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;

public class CommonClass
{
    public static void init() {
        ArmourMaterialRegistry.init();
        ParticleRegistry.init();
        EffectRegistry.init();
        PotionRegistry.init();
        SoundRegistry.init();
        ItemRegistry.init();
        BlockRegistry.init();
        BlockEntityRegistry.init();
        MenuRegistry.init();
        CreativeTabRegistry.init();
        EntityRegistry.init();
        PacketRegistration.init();
    }
}