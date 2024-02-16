package com.tristian.monumentabaernecessities.api;

import com.google.gson.JsonObject;

import java.util.Optional;

/**
 *  So here's what we want to make sure we have:
 *  Name,
 *  Location,
 *  Masterwork if applicable
 *  Stats
 *  Enchants
 *  Tier
 *  Region
 *  The base minecraft item
 *  the lore of the item
 *  Type
 */
public class MonumentaItem {

    private final String name;
    private final String location;
    private final String tier;
    private final String baseItem;
    private final String releaseStatus;
    private final String nbt;
    private final String type;
    private final String plainLore;
    private final JsonObject stats;
    private final String region;

    public MonumentaItem(String name, String region, String location, String tier, String baseItem, String releaseStatus, String nbt, String type, JsonObject stats, String lore) {
        this.name = name;
        this.location = location;
        this.tier = tier;
        this.baseItem = baseItem;
        this.releaseStatus = releaseStatus;
        this.nbt = nbt;
        this.type = type;
        this.stats = stats;
        this.plainLore = lore;
        this.region = region;
    }

    @Override
    public String toString() {
        return "MonumentaItem{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", tier='" + tier + '\'' +
                ", baseItem='" + baseItem + '\'' +
                ", releaseStatus='" + releaseStatus + '\'' +
                ", nbt='" + nbt + '\'' +
                ", type='" + type + '\'' +
                ", stats=" + stats +
                ", lore=\""+ plainLore +'"' +
                ", region="+region+
                '}';
    }
}
