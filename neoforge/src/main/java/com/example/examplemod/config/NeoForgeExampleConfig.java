package com.example.examplemod.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class NeoForgeExampleConfig extends ExampleConfig {

    public static final ModConfigSpec SPEC;
    private static final ModConfigSpec.BooleanValue ENABLE_EXAMPLE_FEATURE;
    private static final ModConfigSpec.IntValue MAX_STACK_OVERRIDE;
    private static final ModConfigSpec.ConfigValue<String> GREETING;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.comment("Example Mod Configuration").push("general");
        ENABLE_EXAMPLE_FEATURE = builder
                .comment("Enables the example feature.")
                .define("enableExampleFeature", true);
        MAX_STACK_OVERRIDE = builder
                .comment("Maximum stack size override for mod items (1-64).")
                .defineInRange("maxStackOverride", 64, 1, 64);
        GREETING = builder
                .comment("Greeting message displayed on login.")
                .define("greeting", "Welcome to Example Mod!");
        builder.pop();

        SPEC = builder.build();
    }

    @Override public boolean enableExampleFeature() { return ENABLE_EXAMPLE_FEATURE.get(); }
    @Override public int maxStackOverride()          { return MAX_STACK_OVERRIDE.get(); }
    @Override public String greeting()               { return GREETING.get(); }
}