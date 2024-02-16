package com.tristian.monumentabaernecessities.api.enums;

public enum Region {
    VALLEY("Valley"),
    ISLES("Isles"),
    RING("Ring"),
    NIL(null),
    ;


    private final String jsonValue;

    Region(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public String getJsonValue() {
        return jsonValue;
    }
}
