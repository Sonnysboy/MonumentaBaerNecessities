package com.tristian.monumentabaernecessities.api.situationals;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;

/**
 *  Holds some values to help situationals that rely on changing values.
 */
public class SituationalListener {
    public static float lastAbsorption = 0;
    public static float lastHp = 0;
    public static long damageTakenTime = 0;
    public static long timeSinceLastDamage = 0;


    static {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            var player = client.player;
            float hp = player.getHealth();
            float absorption = player.getAbsorptionAmount();
            if (lastHp > hp || lastAbsorption > absorption) {
                damageTakenTime  = System.currentTimeMillis();
            }
            lastHp = hp;
            lastAbsorption = absorption;
            timeSinceLastDamage = System.currentTimeMillis() - damageTakenTime;
        });
    }

}
