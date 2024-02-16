package com.tristian.monumentabaernecessities.api;

import com.google.gson.JsonObject;
import com.tristian.monumentabaernecessities.api.stats.ItemStats;
import org.jetbrains.annotations.Nullable;

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


    // as stated in the json.
    private final String internalKey;

    @Nullable
    private final String name;
    @Nullable
    private final String location;
    @Nullable
    private final String tier;
    @Nullable
    private final String plainLore;

    private final String baseItem;
    private final String releaseStatus;
    private final String nbt;
    private final String type;
    private final ItemStats stats;
    private final String region;

    public MonumentaItem(String internalKey,
                         @Nullable String name,
                         String region,
                         @Nullable String location,
                         @Nullable String tier,
                         String baseItem,
                         String releaseStatus,
                         String nbt,
                         String type,
                         ItemStats stats,
                         @Nullable String lore) {
        this.internalKey = internalKey;
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
        return "MonumentaItem("+internalKey+"): "+"{" +
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
