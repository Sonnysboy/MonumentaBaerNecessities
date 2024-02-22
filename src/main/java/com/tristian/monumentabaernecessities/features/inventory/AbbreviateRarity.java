package com.tristian.monumentabaernecessities.features.inventory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.tristian.monumentabaernecessities.api.Items;
import com.tristian.monumentabaernecessities.api.MonumentaItem;
import com.tristian.monumentabaernecessities.api.enums.Tiers;
import com.tristian.monumentabaernecessities.utils.ItemColors;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;

/**
 * Show rarity as a letter while rendering an item.
 */
public class AbbreviateRarity {


    public static void onDrawItemInSlot(DrawContext context, TextRenderer textRenderer, ItemStack stack, int x, int y, String countOverride) {
        if (stack.isEmpty()) return;


        Items.fromNbt(stack.getNbt()).flatMap(MonumentaItem::getTier).ifPresent(tier -> {
            String text = String.valueOf(tier.getJsonValue().charAt(0));
            context.getMatrices().translate(0.0F, 0.0F, 200.0F);
            context.drawText(textRenderer, text, x + 6 - textRenderer.getWidth(text), y + 1, ItemColors.getColorForTier(tier.getJsonValue()), true);
        });

    }
}
