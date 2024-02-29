package com.tristian.monumentabaernecessities;

import ch.njol.minecraft.config.Config;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.tristian.monumentabaernecessities.api.Items;
import com.tristian.monumentabaernecessities.features.Features;
import com.tristian.monumentabaernecessities.features.debug.ItemDebuggingHelpers;
import com.tristian.monumentabaernecessities.features.inventory.AbbreviateRarity;
import com.tristian.monumentabaernecessities.features.overlays.CZCharmOverlay;
import com.tristian.monumentabaernecessities.features.player.PsPlayer;
import com.tristian.monumentabaernecessities.locations.Locations;
import com.tristian.monumentabaernecessities.options.Options;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

// ay btw i aint ever written a fabric mod or anything above 1.12.2 forge so this is gonna be fun
public class MonumentaBaerNecessities implements ClientModInitializer {

    public static final String MOD_IDENTIFIER = "monumenta-baer-necessities";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_IDENTIFIER);




    public static MinecraftClient mc;


    public static final String OPTIONS_FILE_NAME = "monumenta-baer-necessities.json";

    public static Options options = new Options();

    public static void saveConfig() {
        MinecraftClient.getInstance().execute(() -> {
            try {
                Config.writeJsonFile(options, OPTIONS_FILE_NAME);
            } catch (IOException ignored) {
            }
        });
    }

    @Override
    public void onInitializeClient() {
        mc = MinecraftClient.getInstance();

        loadItems();


        try {
            options = Config.readJsonFile(Options.class, OPTIONS_FILE_NAME);
        } catch (FileNotFoundException e) {
            // Config file doesn't exist, so use default config (and write config file).
            try {
                Config.writeJsonFile(options, OPTIONS_FILE_NAME);
            } catch (IOException ex) {
                // ignore
            }
        } catch (IOException | JsonParseException e) {
            // Any issue with the config file silently reverts to the default config
            LOGGER.error("Caught error whilst trying to load configuration file", e);
        }
        Features.loadFeatures(); // laod features last.

    }

    private static void loadItems() {
        LOGGER.info("Loading Monu items...");
        Items.load();


    }
}