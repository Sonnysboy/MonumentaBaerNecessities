package com.tristian.monumentabaernecessities.features.inventory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.tristian.monumentabaernecessities.MonumentaBaerNecessities;
import com.tristian.monumentabaernecessities.api.Items;
import com.tristian.monumentabaernecessities.utils.ItemColors;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;

import java.awt.*;

/**
 * Outline an item in an inventory if it's from a wool dungeon.
 */
public class ColorByWoolDungeon {

    public static void onSlotDrawn(DrawContext context, Slot slot) {
        if (!MonumentaBaerNecessities.options.inventoryWoolItemOutlines) return;



        Items.fromNbt(slot.getStack().getNbt()).ifPresent(x -> {
            int color;
            if ((color = ItemColors.getColorForLocation(x.getLocation().getJsonValue())) == ItemColors.DEFAULT_COLOR)  return;
            RenderSystem.enableDepthTest();
            context.getMatrices().push();
            context.getMatrices().translate(0, 0, 100);
//            context.setShaderColor(1f,1f,1f,1f);
            context.fill(slot.x, slot.y, 16 + slot.x, 16 + slot.y, 0x88000000 | color);
            context.getMatrices().pop();
            context.draw();
        });
    }


}
