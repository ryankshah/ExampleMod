package com.example.examplemod.effect;

import com.example.examplemod.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ExampleEffect extends MobEffect {

    public ExampleEffect() {
        super(
            MobEffectCategory.BENEFICIAL,  // BENEFICIAL (blue), HARMFUL (red), NEUTRAL (grey)
            0x5C8CFF                        // particle and icon border colour as an RGB integer
        );

        // Increase max health by 2 per amplifier level while the effect is active
        addAttributeModifier(
                Attributes.MAX_HEALTH,
                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "example_effect.max_health"),
                2.0,
                AttributeModifier.Operation.ADD_VALUE
        );
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide()) {
            // amplifier is 0-based: level I = 0, level II = 1, level III = 2
            float healAmount = 1.0f + amplifier;
            if (entity.getHealth() < entity.getMaxHealth()) {
                entity.heal(healAmount);
            }
        }
        return true; // returning false removes the effect immediately
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        // Apply every (40 >> amplifier) ticks: level I every 40 ticks, level II every 20, etc.
        int interval = Math.max(1, 40 >> amplifier);
        return duration % interval == 0;
    }
}