package com.example.examplemod.registry;

import com.example.examplemod.Constants;
import com.example.examplemod.registration.RegistrationProvider;
import com.example.examplemod.registration.RegistryObject;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;

public class ArmourMaterialRegistry {

    public static final RegistrationProvider<ArmorMaterial> ARMOUR_MATERIALS =
            RegistrationProvider.get(Registries.ARMOR_MATERIAL, Constants.MOD_ID);

    public static final RegistryObject<ArmorMaterial, ArmorMaterial> EXAMPLE =
            ARMOUR_MATERIALS.register("example", () -> new ArmorMaterial(
                Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                    map.put(ArmorItem.Type.HELMET,     2);
                    map.put(ArmorItem.Type.CHESTPLATE, 6);
                    map.put(ArmorItem.Type.LEGGINGS,   5);
                    map.put(ArmorItem.Type.BOOTS,      2);
                }),
                15,
                SoundEvents.ARMOR_EQUIP_IRON,
                () -> Ingredient.of(ItemRegistry.IRON_STICK.get()),
                List.of(new ArmorMaterial.Layer(
                        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "example"))),
                0.0f,
                0.0f
            ));

    public static void init() {}
}