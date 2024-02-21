package com.tristian.monumentabaernecessities.api;

import com.google.gson.JsonObject;
import com.tristian.monumentabaernecessities.api.enums.Locations;
import com.tristian.monumentabaernecessities.api.enums.Regions;
import com.tristian.monumentabaernecessities.api.enums.Tiers;
import com.tristian.monumentabaernecessities.api.stats.ItemStats;

public class ItemParser {


    /** Some fields in the json object can be null. Those fields are:
     * <ul>
     *     <li> location </li>
     *     <li> region </li>
     *     <li> tier </li>
     *     <li> name</li>
     *     <li> lore</li>
     *
     * </ul>
     *
     * @param key The key's name given by the api.
     * @param object The JsonObject from the api.
     * @return A MonumentaItem represented by the given json data.
     */
    public static MonumentaItem decode(String key, JsonObject object) {



//        System.out.println("for object : " + object);

        Locations location = Locations.NIL;
        Regions region = Regions.NIL;
        Tiers tier   = Tiers.NIL;
        String name   = null;
        String lore   = null;

//we handle all the potential nulls first
        if (object.has("location")) {
            location = Locations.fromJson(object.get("location").getAsString());
        }

        if (object.has("region")) {
            region = Regions.fromJson(object.get("region").getAsString());
        }

        if (object.has("tier")) {
            tier = Tiers.fromJson(object.get("tier").getAsString());
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
        MonumentaItem ret = new MonumentaItem(key, name, region, location, tier, baseItem, releaseStatus, nbt, type, parseStats(stats), lore);
        if (object.has("power")) {
            assert object.has("class_name");
            ret.setCharmData(new MonumentaItem.CharmData(object.get("class_name").getAsString(), object.get("power").getAsInt()));
        }
        return ret;
    }

    private static ItemStats parseStats(JsonObject object) {
        final ItemStats stats = new ItemStats();
        object.asMap().forEach((key, value) -> stats.put(key, value.getAsDouble()));
        return stats;
    }


}
