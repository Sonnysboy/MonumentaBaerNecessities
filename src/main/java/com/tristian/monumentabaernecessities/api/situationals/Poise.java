package com.tristian.monumentabaernecessities.api.situationals;

import net.minecraft.client.MinecraftClient;

public class Poise extends Situational{
    public Poise() {
        super("poise");
    }

    @Override
    public boolean isActive() {
        if (!isEquipped()) return false;
        if (MinecraftClient.getInstance().player == null) return false;
        return MinecraftClient.getInstance().player.getHealth() / MinecraftClient.getInstance().player.getHealth() >= 0.9;
    }
}
