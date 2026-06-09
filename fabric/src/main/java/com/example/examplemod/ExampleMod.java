package com.example.examplemod;

import com.example.examplemod.config.ExampleConfig;
import com.example.examplemod.config.FabricExampleConfig;
import net.fabricmc.api.ModInitializer;

public class ExampleMod implements ModInitializer
{
    @Override
    public void onInitialize() {
        ExampleConfig.setInstance(FabricExampleConfig.load());
        CommonClass.init();
    }
}
