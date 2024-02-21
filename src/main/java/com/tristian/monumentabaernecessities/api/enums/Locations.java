package com.tristian.monumentabaernecessities.api.enums;

public enum Locations {

    WHITE("White"),
    ORANGE("Orange"),
    MAGENTA("Magenta"),
    LIGHT_BLUE("Light Blue"),
    YELLOW("Yellow"),
    LIME("Lime"),
    PINK("Pink"),
    GRAY("Gray"),
    LIGHT_GRAY("Light Gray"),
    CYAN("Cyan"),
    PURPLE("Purple"),
    TEAL("Teal"),
    SHIFTING("Shifting"),
    FORUM("Forum"),
    TOV("TOV"),
    DOCKS("Docks"),
    CARNIVAL("Carnival"),
    DELVES("Delves"),
    RUSH/*_OF_DISSONANCE*/("Rush"),
    HORSEMAN("Horseman"),
    BLACK_MIST("Mist"),
    SEALED_REMORSE("Remorse"),
    REMORSEFUL_SKIN("Remorseful Skin"),//whatever that means
    DARKEST_DEPTHS("Depths"),
    ELDRASK("Eldrask"),
    TITANIC_SKIN("Titanic Skin"),
    HEKAWT("Hekawt"),
    ETERNITY_SKIN("Eternity Skin"), // whatever these are
    ISLES_DELVES("Isles Delves"),
    ISLES_CASINO("Isles Casino"),
    ISLES_OVERWORLD("Isles Overworld"),
    CELSIAN_ISLES("Celsian Isles"),
    VALLEY_DELVES("Valley Delves"),
    ARMORY("Armory"),
    VALLEY_CASINO("Valley Casino"),
    VALLEY_OVERWORLD("Valley Overworld"),
    KINGS_VALLEY("King's Valley"),
    LOWTIDE_SMUGGLER("Lowtide Smuggler"),
    AZACOR("Azacor"),
    LABS("Labs"),
    WILLOWS("Willows"),
    STORIED_SKIN("Storied Skin"),
    FORSWORN_SANCTUM("Sanctum"),
    VERDANT_REMNANTS("Verdant"),
    EPHEMERAL_CORRIDORS("Corridors"),
    EPHEMERAL_ENHANCEMENTS("Ephemeral Enhancements"),
    MALEVOLENT_REVERIE("Reverie"),
    KAUL("Kaul"),
    SILVER_KNIGHTS_TOMB("SKT"),
    THE_WOLFSWOOD("The Wolfswood"),
    BLUE("Blue"),
    BROWN("Brown"),
    PORTAL_STRIKE("Portal"),
    PELIAS_KEEP("Pelias' Keep"),
    RUIN("Ruin"), // whatver this is
    SANGUINE_HALLS("Sanguine Halls"),
    MARINA_NOIR("Marina Noir"),
    QUEST_REWARD("Quest Reward"),
    TRANSMOGRIFIER("Transmogrifier"),
    ARCHITECTS_RING("Architect's Ring"),
    GODSPORE("Godspore"),
    SEASONAL_PASS("Seasonal Pass"),
    MYTHIC_RELIQUARY("Mythic Reliquary"),
    BLITZ("Blitz"),
    RING_CASINO("Ring Casino"),
    UGANDA("Uganda"), //????
    ARENA_OF_TERTH("Arena of Terth"),
    SOULWOVEN("Soulwoven"),
    VALENTINES_DAY("Valentine's Day"),
    GALLERY_OF_FEAR("Gallery of Fear"),
    THREADWARPED_SKIN("Threadwarped Skin"),
    INTELLECT_CRYSTALLIZER("Intellect Crystallizer"),
    THE_HOARD("The Hoard"),
    GREED_SKIN("Greed Skin"),
    DIVINE_SKIN("Divine Skin"),
    APRIL_FOOLS("April's Fools"),
    THE_ETERNAL_VIGIL("The Eternal Vigil"),
    WINTER_EVENT("Winter Event"),
    HOLIDAY_SKIN("Holday Skin"),
    HALLOWEEN_EVENT("Halloween Event"),
    FISHING("Fishing"),
    CHALLENGER("Challenger"),
    TRUE_NORTH("True North"),
    STARPOINT("Starpoint"),
    NIL("")
    ;


    private final String jsonValue;

    Locations(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    /**
     *
     * @param value The value returned from the monu api, can be null
     * @return The <code>Locations</code> value corresponding to the json value, or
     */
    public static Locations fromJson(String value) {
        for (Locations location: values()) {
            if (location == NIL) continue;
            if (location.getJsonValue().equals(value)) return location;
        }
        return NIL;
    }

    public String getJsonValue() {
        return jsonValue;
    }
}
