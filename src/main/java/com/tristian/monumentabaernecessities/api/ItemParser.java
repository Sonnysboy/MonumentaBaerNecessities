package com.tristian.monumentabaernecessities.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tristian.monumentabaernecessities.api.enums.Locations;
import com.tristian.monumentabaernecessities.api.enums.Regions;
import com.tristian.monumentabaernecessities.api.enums.Tiers;
import com.tristian.monumentabaernecessities.api.stats.ItemStats;

import java.util.Optional;

public class ItemParser {


    /**
     * Some fields in the json object can be null. Those fields are:
     * <ul>
     *     <li> location </li>
     *     <li> region </li>
     *     <li> tier </li>
     *     <li> name</li>
     *     <li> lore</li>
     *
     * </ul>
     *
     * @param key    The key's name given by the api.
     * @param object The JsonObject from the api.
     * @return A MonumentaItem represented by the given json data.
     */
    public static MonumentaItem decode(String key, JsonObject object) {


//        System.out.println("for object : " + object);

//        we have a decision to make here. we're wasting space by doing this but it makes the code look nice
        Locations location = Optional.ofNullable(object.get("location")).map(JsonElement::getAsString).flatMap(Locations::fromJson).orElse(null);
        Regions region = Optional.ofNullable(object.get("region")).map(JsonElement::getAsString).flatMap(Regions::fromJson).orElse(null);
        Tiers tier = Optional.ofNullable(object.get("tier")).map(JsonElement::getAsString).flatMap(Tiers::fromJson).orElse(null);
        String name = Optional.ofNullable(object.get("name")).map(JsonElement::getAsString).orElse(null);
        String lore = Optional.ofNullable(object.get("lore")).map(JsonElement::getAsString).orElse(null);

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
