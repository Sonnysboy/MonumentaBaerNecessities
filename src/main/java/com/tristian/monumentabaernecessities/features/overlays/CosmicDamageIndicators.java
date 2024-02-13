package com.tristian.monumentabaernecessities.features.overlays;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;

//throwback
public class CosmicDamageIndicators {

    public static void drawDamageNumber(MatrixStack matrix, int dmg, double x, double y,
                                        float width) {
        int i = Math.abs(Math.round(dmg));
        if (i == 0) {
            return;
        }
        String s = Integer.toString(i);
        MinecraftClient minecraft = MinecraftClient.getInstance();
        int sw = minecraft.textRenderer.getWidth(s);
        int color = dmg < 0 ? 0x00ff00 : 16733525;
    }
}
