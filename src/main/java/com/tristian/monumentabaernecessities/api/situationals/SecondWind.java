package com.tristian.monumentabaernecessities.api.situationals;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class SecondWind extends Situational {

    public SecondWind() {
        super("second_wind");
    }

    @Override
    public boolean isActive() {
        if (!isEquipped()) return false;
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return false;
        return player.getHealth() / player.getMaxHealth() <= 0.5;
    }
}
