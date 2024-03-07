package com.tristian.monumentabaernecessities.api.situationals;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;

public class Reflexes extends Situational {
    public Reflexes() {
        super("reflexes");
    }

    @Override
    public boolean isActive() {
        return isEquipped() && SituationalHelper.getNearbyEnemeis(8) > 3;
    }

}
