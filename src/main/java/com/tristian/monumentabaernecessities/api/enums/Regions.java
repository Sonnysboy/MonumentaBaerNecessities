package com.tristian.monumentabaernecessities.api.enums;

import java.util.Optional;

public enum Regions {
    VALLEY("Valley"),
    ISLES("Isles"),
    RING("Ring"),
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
     * @param value The value returned from the monu api.
     * @return An Optional containing the <code>Regions</code>, or empty.
     */
    public static Optional<Regions> fromJson(String value) {
        for (Regions regions : values()) {
            if (regions.getJsonValue().equals(value)) return Optional.of(regions);
        }
        return Optional.empty();
    }


}
