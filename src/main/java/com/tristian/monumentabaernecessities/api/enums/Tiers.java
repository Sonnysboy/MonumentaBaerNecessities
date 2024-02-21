package com.tristian.monumentabaernecessities.api.enums;

public enum Tiers {


    EPIC("Epic"),
    PATRON("Patron"),
    UNIQUE("Unique"),
    TROPHY("Trophy"),
    UNCOMMON("Uncommon"),
    KEY("Key"),
    RARE("Rare"),
    CURRENCY("Currency"),
    ARTIFACT("Artifact"),
    NIL(""); // some items have no tier

    private final String jsonValue;

    Tiers(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public String getJsonValue() {
        return jsonValue;
    }


    public static Tiers fromJson(String value) {
        for (Tiers tiers : values()) {
            if (tiers == NIL) continue;
            if (tiers.getJsonValue().equals(value)) return tiers;
        }
        return NIL;
    }
}
