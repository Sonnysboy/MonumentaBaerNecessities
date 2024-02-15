package com.tristian.monumentabaernecessities.enums;

public enum WoolDungeons {
    // r1
    WHITE("Halls of Wind and Blood", Region.VALLEY),
    ORANGE("Fallen Menagerie", Region.VALLEY),
    MAGENTA("Plagueroot Temple", Region.VALLEY),
    LIGHT_BLUE("Arcane Rivalry", Region.VALLEY),
    YELLOW("Vernal Nightmare", Region.VALLEY),
    // r2
    LIME("Salazar's Folly", Region.ISLES),
    PINK("Harmonic Arboretumm", Region.ISLES),
    GRAY("Valley of Forgotten Pharaohs", Region.ISLES),
    LIGHT_GRAY("Palace of Mirrors", Region.ISLES),
    CYAN("Scourge of Lunacy", Region.ISLES),
    PURPLE("Grasp of Avarice", Region.ISLES),
    TEAL("Echoes of Oblivion", Region.ISLES),
    // r3
    BLUE("Coven's Gambit", Region.RING),
    BROWN("Cradle of the Broken God", Region.RING);

    private final String name;
    private final Region region;

    WoolDungeons(String name, Region region) {
        this.name = name;
        this.region = region;
    }

    public Region getRegion() {
        return region;
    }

    public String getName() {
        return name;
    }

}
