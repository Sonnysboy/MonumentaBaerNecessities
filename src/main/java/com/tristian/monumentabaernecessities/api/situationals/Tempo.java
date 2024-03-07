package com.tristian.monumentabaernecessities.api.situationals;

public class Tempo extends Situational {
    public Tempo() {
        super("tempo");
    }

    @Override
    public boolean isActive() {
        return isEquipped() && SituationalListener.timeSinceLastDamage > 4000
                || ((SituationalListener.timeSinceLastDamage > 2000 && SituationalListener.timeSinceLastDamage < 4000));
    }

}
