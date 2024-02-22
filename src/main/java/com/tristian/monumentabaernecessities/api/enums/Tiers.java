package com.tristian.monumentabaernecessities.api.enums;

import java.util.Optional;

public enum Tiers {


    EPIC("Epic"),
    PATRON("Patron"),
    UNIQUE("Unique"),
    TROPHY("Trophy"),
    UNCOMMON("Uncommon"),
    KEY("Key"),
    RARE("Rare"),
    CURRENCY("Currency"),
    ARTIFACT("Artifact");

    private final String jsonValue;

    Tiers(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public String getJsonValue() {
        return jsonValue;
    }


    /**
     *
     *
     * @param value The value returned from the monu api.
     * @return An Optional containing the <code>Tiers</code>, or empty.
     */
    public static Optional<Tiers> fromJson(String value) {
        for (Tiers tiers : values()) {
            if (tiers.getJsonValue().equals(value)) return Optional.of(tiers);
        }
        return Optional.empty(); // some items have no tier
    }
}
