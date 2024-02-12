package com.tristian.monumentabaernecessities;

import ch.njol.minecraft.config.Config;
import com.google.gson.JsonParseException;
import com.tristian.monumentabaernecessities.features.CZCharmOverlay;
import com.tristian.monumentabaernecessities.locations.Locations;
import com.tristian.monumentabaernecessities.options.Options;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

// ay btw i aint ever written a fabric mod or anything above 1.12.2 forge so this is gonna be fun
public class MonumentaBaerNecessities implements ClientModInitializer {

	public static final String MOD_IDENTIFIER = "monumenta-baer-necessities";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_IDENTIFIER);


	public static Locations locations = new Locations();


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

		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		ItemTooltipCallback.EVENT.register(CZCharmOverlay::onItemTooltip
		);
	}
	// credit: Unofficial Monumenta Mod
	public static boolean isOnMonumenta() {
		boolean onMM = false;
		MinecraftClient mc = MinecraftClient.getInstance();
		String shard = Locations.getShard();

		if (!Objects.equals(shard, "unknown")) {
			onMM = true;
		}

		if (!onMM && mc.getCurrentServerEntry() != null) {
			onMM = !mc.isInSingleplayer() && mc.getCurrentServerEntry().address.toLowerCase().endsWith(".playmonumenta.com");
		}
		return onMM;
	}
}