package com.tristian.monumentabaernecessities.api;

import com.tristian.monumentabaernecessities.api.enums.Locations;
import com.tristian.monumentabaernecessities.api.enums.Regions;
import com.tristian.monumentabaernecessities.api.enums.Tiers;
import com.tristian.monumentabaernecessities.api.stats.ItemStats;
import org.jetbrains.annotations.Nullable;

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


    private final String internalKey;

    // some items have no name key
    @Nullable
    private final String name;


    private final Locations location;

    /**
     * The tier of the item.
     */
    private final Tiers tier;


    // some items have no plainLore key
    @Nullable
    private final String plainLore;

    private final String baseItem;

    private final String releaseStatus;

    private final String nbt;

    private final String type;

    private final ItemStats stats;

    private final Regions region;
    @Nullable
    private CharmData charmData;


    /**
     *
     *
     * @param internalKey   The internal key of the item.
     * @param name          The name of the item. This field is nullable, some entries in the api do not contain names
     * @param region        The region of the item.
     * @param location      The location of the item.
     * @param tier          The tier of the item.
     * @param baseItem      The base item of the item.
     * @param releaseStatus The release status of the item.
     * @param nbt           The NBT data of the item.
     * @param type          The type of the item.
     * @param stats         The stats of the item.
     * @param lore          The lore or additional information about the item. This field is nullable, some entries in the api do not contain plain lores
     * */
    public MonumentaItem(String internalKey,
                         @Nullable String name,
                         Regions region,
                         Locations location,
                         Tiers tier,
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

    // Methods

    /**
     * Returns the internal key of the item.
     *
     * @return The internal key of the item.
     */
    public String getInternalKey() {
        return internalKey;
    }

    /**
     * Returns the name of the item, if available.
     *
     * @return An Optional containing the name of the item, if available.
     */
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    /**
     * Returns the location of the item.
     *
     * @return The location of the item.
     */
    public Locations getLocation() {
        return location;
    }

    /**
     * Returns the tier of the item.
     *
     * @return The tier of the item.
     */
    public Tiers getTier() {
        return tier;
    }

    /**
     * Returns the plain lore or additional information about the item, if available.
     *
     * @return An Optional containing the plain lore of the item, if available.
     */
    public Optional<String> getPlainLore() {
        return Optional.ofNullable(plainLore);
    }

    /**
     * Returns the base item of the item.
     *
     * @return The base item of the item.
     */
    public String getBaseItem() {
        return baseItem;
    }

    /**
     * Returns the release status of the item.
     *
     * @return The release status of the item.
     */
    public String getReleaseStatus() {
        return releaseStatus;
    }

    /**
     * Returns the NBT data of the item.
     *
     * @return The NBT data of the item.
     */
    public String getNbt() {
        return nbt;
    }

    /**
     * Returns the type of the item.
     *
     * @return The type of the item.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the stats of the item.
     *
     * @return The stats of the item.
     */
    public ItemStats getStats() {
        return stats;
    }

    /**
     * Returns the region of the item.
     *
     * @return The region of the item.
     */
    public Regions getRegion() {
        return region;
    }

    /**
     * Returns a string representation of the item.
     *
     * @return A string representation of the item.
     */
    @Override
    public String toString() {
        String ret = "MonumentaItem(" + internalKey + "): " + "{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", tier='" + tier + '\'' +
                ", baseItem='" + baseItem + '\'' +
                ", releaseStatus='" + releaseStatus + '\'' +
                ", nbt='" + nbt + '\'' +
                ", type='" + type + '\'' +
                ", stats=" + stats +
                ", lore=\"" + plainLore + '"' +
                ", region=" + region;
        if (this.charmData != null) {
            ret += "," + "\ncharm info:\nclass_name="+getCharmData().get().className + ", power="+getCharmData().get().charmPower;
        }

        return ret + "}";
    }


    protected void setCharmData(@Nullable CharmData data) {
        this.charmData =data;
    }

    /**
     *
     * @return The data if it's a charm, or Optional.empty if it's not.
     */
    public Optional<CharmData> getCharmData() {
        return Optional.ofNullable(charmData);
    }
    // charm data for charms
    protected record CharmData(
            String className,
            int charmPower
    ) {}
}
