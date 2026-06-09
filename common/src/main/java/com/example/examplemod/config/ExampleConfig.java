package com.example.examplemod.config;

public abstract class ExampleConfig {

    private static ExampleConfig INSTANCE;

    public static ExampleConfig get() {
        if (INSTANCE == null) throw new IllegalStateException("Config not yet initialised");
        return INSTANCE;
    }

    public static void setInstance(ExampleConfig config) {
        INSTANCE = config;
    }

    // Config values: add your own fields here
    public abstract boolean enableExampleFeature();
    public abstract int maxStackOverride();
    public abstract String greeting();
}