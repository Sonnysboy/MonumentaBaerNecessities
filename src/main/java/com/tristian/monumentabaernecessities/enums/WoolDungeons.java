package com.tristian.monumentabaernecessities.enums;

import com.tristian.monumentabaernecessities.api.enums.Regions;

public enum WoolDungeons {
    // r1
    WHITE("Halls of Wind and Blood", Regions.VALLEY),
    ORANGE("Fallen Menagerie", Regions.VALLEY),
    MAGENTA("Plagueroot Temple", Regions.VALLEY),
    LIGHT_BLUE("Arcane Rivalry", Regions.VALLEY),
    YELLOW("Vernal Nightmare", Regions.VALLEY),
    // r2
    LIME("Salazar's Folly", Regions.ISLES),
    PINK("Harmonic Arboretum", Regions.ISLES),
    GRAY("Valley of Forgotten Pharaohs", Regions.ISLES),
    LIGHT_GRAY("Palace of Mirrors", Regions.ISLES),
    CYAN("Scourge of Lunacy", Regions.ISLES),
    PURPLE("Grasp of Avarice", Regions.ISLES),
    TEAL("Echoes of Oblivion", Regions.ISLES),
    // r3
    BLUE("Coven's Gambit", Regions.RING),
    BROWN("Cradle of the Broken God", Regions.RING);

    private final String name;
    private final Regions regions;

    WoolDungeons(String name, Regions regions) {
        this.name = name;
        this.regions = regions;
    }

    public Regions getRegion() {
        return regions;
    }

    public String getName() {
        return name;
    }

}
