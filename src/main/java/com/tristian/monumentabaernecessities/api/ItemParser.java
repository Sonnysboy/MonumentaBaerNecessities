package com.tristian.monumentabaernecessities.api;

import com.google.gson.JsonObject;
import com.tristian.monumentabaernecessities.api.stats.ItemStats;

public class ItemParser {



    public static MonumentaItem decode(JsonObject object) {


        if (object.has("power")) {
            System.out.println("charm detected, skipping for now.");
            return null;
        }

        System.out.println("for object : " + object);
        String location = object.get("location").getAsString();

        String region = object.get("region").getAsString();

        String tier = object.get("tier").getAsString();

        String name = object.get("name").getAsString();

        String baseItem = object.get("base_item").getAsString();

        String releaseStatus = object.get("release_status").getAsString();

        String nbt = object.get("nbt").getAsString();

        String type = object.get("type").getAsString();

        JsonObject stats = object.get("stats").getAsJsonObject();

        // this has no formatting codes, we'll have to use nbt for that.
        String lore = object.get("lore").getAsString();
        return new MonumentaItem(name, region, location, tier, baseItem, releaseStatus, nbt, type, stats, lore);
    }

    private static ItemStats parseStats(JsonObject object) {
        final ItemStats stats = new ItemStats();
        object.asMap().forEach((key, value) -> stats.put(key, value.getAsDouble()));
        return stats;
    }


}
