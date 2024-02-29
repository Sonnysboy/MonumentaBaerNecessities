package com.tristian.monumentabaernecessities.features.inventory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.tristian.monumentabaernecessities.MonumentaBaerNecessities;
import com.tristian.monumentabaernecessities.api.Items;
import com.tristian.monumentabaernecessities.api.MonumentaItem;
import com.tristian.monumentabaernecessities.api.events.SlotDrawnCallback;
import com.tristian.monumentabaernecessities.api.features.Feature;
import com.tristian.monumentabaernecessities.utils.ItemColors;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.screen.slot.Slot;

/**
 * Outline an item in an inventory if it's from a wool dungeon.
 */
public class ColorByWoolDungeon extends Feature {

    public void init() {
        SlotDrawnCallback.EVENT.register(this::onSlotDrawn);
    }

    private void onSlotDrawn(DrawContext context, Slot slot) {
        if (!MonumentaBaerNecessities.options.inventoryWoolItemOutlines) return;
        Items.fromNbt(slot.getStack().getNbt()).flatMap(MonumentaItem::getLocation).ifPresent(location -> {
            int color;
            if ((color = ItemColors.getColorForLocation(location.getJsonValue())) == ItemColors.DEFAULT_COLOR) return;

            context.getMatrices().push();

            RenderSystem.enableDepthTest();
            RenderSystem.disableBlend();

            context.getMatrices().translate(0, 0, 100);
            context.setShaderColor(1f, 1f, 1f, 1f);

            context.fillGradient(slot.x, slot.y, slot.x + 16, slot.y + 16, 0x88000000 | color, 0x88000000 | color);
            context.draw();
            context.getMatrices().pop();
        });
    }


}
