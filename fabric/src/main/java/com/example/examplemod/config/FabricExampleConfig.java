package com.example.examplemod.config;

import com.example.examplemod.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class FabricExampleConfig extends ExampleConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance()
            .getConfigDir().resolve("examplemod.json");

    private boolean enableExampleFeature = true;
    private int maxStackOverride = 64;
    private String greeting = "Welcome to Example Mod!";

    public static FabricExampleConfig load() {
        FabricExampleConfig config;
        if (Files.exists(CONFIG_PATH)) {
            try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {
                config = GSON.fromJson(reader, FabricExampleConfig.class);
            } catch (IOException e) {
                Constants.LOG.error("Failed to read config, using defaults", e);
                config = new FabricExampleConfig();
            }
        } else {
            config = new FabricExampleConfig();
        }
        config.save();
        return config;
    }

    private void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            try (Writer writer = Files.newBufferedWriter(CONFIG_PATH)) {
                GSON.toJson(this, writer);
            }
        } catch (IOException e) {
            Constants.LOG.error("Failed to write config", e);
        }
    }

    @Override public boolean enableExampleFeature() { return enableExampleFeature; }
    @Override public int maxStackOverride()          { return maxStackOverride; }
    @Override public String greeting()               { return greeting; }
}