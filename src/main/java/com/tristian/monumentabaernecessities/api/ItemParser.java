package com.tristian.monumentabaernecessities.api;

import com.google.gson.JsonObject;
import com.tristian.monumentabaernecessities.api.enums.Locations;
import com.tristian.monumentabaernecessities.api.stats.ItemStats;

public class ItemParser {



    public static MonumentaItem decode(String key, JsonObject object) {


        if (object.has("power")) {
            System.out.println("charm detected, skipping for now.");
            return null;
        }

        System.out.println("for object : " + object);

        Locations location = Locations.NIL;
        String region = null;
        String tier   = null;
        String name   = null;
        String lore   = null;

//we handle all the potential nulls first
        if (object.has("location")) {
            location = Locations.fromJsonKey(object.get("location").getAsString());
        }

        if (object.has("region")) {
            region = object.get("region").getAsString();
        }

        if (object.has("tier")) {
            tier = object.get("tier").getAsString();
        }

        if (object.has("name")) {
            name = object.get("name").getAsString();
        }
        if (object.has("lore")) {
            lore = object.get("lore").getAsString();
        }

        String baseItem = object.get("base_item").getAsString();

        String releaseStatus = object.get("release_status").getAsString();

        String nbt = object.get("nbt").getAsString();

        String type = object.get("type").getAsString();

        JsonObject stats = object.get("stats").getAsJsonObject();

        // this has no formatting codes, we'll have to use nbt for that.
        return new MonumentaItem(key, name, region, location, tier, baseItem, releaseStatus, nbt, type, parseStats(stats), lore);
    }

    private static ItemStats parseStats(JsonObject object) {
        final ItemStats stats = new ItemStats();
        object.asMap().forEach((key, value) -> stats.put(key, value.getAsDouble()));
        return stats;
    }


}
