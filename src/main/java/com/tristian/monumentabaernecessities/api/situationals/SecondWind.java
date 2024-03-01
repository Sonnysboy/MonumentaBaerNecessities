package com.tristian.monumentabaernecessities.api.situationals;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class SecondWind implements Situational {

    @Override
    public boolean isActive() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        assert player != null;
        return player.getHealth() / player.getMaxHealth() <= 0.5;
    }

    @Override
    public String enchantKey() {
        return "Second Wind";
    }
}
