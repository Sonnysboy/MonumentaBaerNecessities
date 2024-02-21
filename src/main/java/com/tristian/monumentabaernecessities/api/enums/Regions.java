package com.tristian.monumentabaernecessities.api.enums;

public enum Regions {
    VALLEY("Valley"),
    ISLES("Isles"),
    RING("Ring"),
    NIL(""), // some items are regionless?
    ;


    private final String jsonValue;

    Regions(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public String getJsonValue() {
        return jsonValue;
    }



    /**
     *
     * @param value The value returned from the monu api, can be null
     * @return The <code>Locations</code> value corresponding to the json value, or
     */
    public static Regions fromJson(String value) {
        for (Regions regions : values()) {
            if (regions == NIL) continue;
            if (regions.getJsonValue().equals(value)) return regions;
        }
        return NIL;
    }


}
