package com.example.examplemod.data;

import com.example.examplemod.Constants;
import com.example.examplemod.registry.SoundRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class ExampleSoundDefinitionsProvider extends SoundDefinitionsProvider {

    public ExampleSoundDefinitionsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Constants.MOD_ID, existingFileHelper);
    }

    @Override
    public void registerSounds() {
        add(SoundRegistry.EXAMPLE_AMBIENT.get(), definition()
                .subtitle("subtitles.examplemod.example_ambient")
                .with(sound(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "example_ambient"))
                        .volume(1.0f)
                        .pitch(1.0f)));
    }
}