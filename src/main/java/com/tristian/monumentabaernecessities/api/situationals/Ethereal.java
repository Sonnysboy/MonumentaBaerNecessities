package com.tristian.monumentabaernecessities.api.situationals;

public class Ethereal extends Situational {
    public Ethereal() {
        super("ethereal");
    }

    @Override
    public boolean isActive() {
        return isEquipped() && SituationalListener.timeSinceLastDamage < 1500;
    }
}
