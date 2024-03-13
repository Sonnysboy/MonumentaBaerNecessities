package com.tristian.monumentabaernecessities.options;

import ch.njol.minecraft.config.ClothConfigSetup;
import ch.njol.minecraft.config.ModMenuConfigSetup;
import ch.njol.minecraft.uiframework.ElementPosition;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import com.tristian.monumentabaernecessities.MonumentaBaerNecessities;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.impl.ConfigEntryBuilderImpl;
import net.minecraft.text.Text;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// credit where credit is due: Unofficial Monumenta Mod
public class ConfigMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ModMenuConfigSetup.getModConfigScreenFactory(MonumentaBaerNecessities.MOD_IDENTIFIER + ".config", () -> MonumentaBaerNecessities.options, new Options());
    }

    public static void registerTypes() {
        ClothConfigSetup.registerType(ElementPosition.class, (value, defaultValue, field, translatePath, saveConsumer) -> {
            List<AbstractConfigListEntry> entries = new ArrayList<>();
            for (Field posField : ElementPosition.class.getDeclaredFields()) {
                entries.add(ClothConfigSetup.buildConfigEntry(value, defaultValue, posField, MonumentaBaerNecessities.MOD_IDENTIFIER + ".config.position"));
            }
            return ConfigEntryBuilderImpl.create()
                    .startSubCategory(Text.translatable(translatePath), entries)
                    .build();
        });
    }
}
