package com.tristian.monumentabaernecessities.api.situationals;

public class Cloaked extends Situational {
    public Cloaked() {
        super("cloaked");
    }

    @Override
    public boolean isActive() {
        return isEquipped() && SituationalHelper.getNearbyEnemeis(5) < 3; // what even is the point of this enchantment
    }
}
