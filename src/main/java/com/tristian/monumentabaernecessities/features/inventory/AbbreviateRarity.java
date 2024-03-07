package com.tristian.monumentabaernecessities.features.inventory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.tristian.monumentabaernecessities.MonumentaBaerNecessities;
import com.tristian.monumentabaernecessities.api.Items;
import com.tristian.monumentabaernecessities.api.MonumentaItem;
import com.tristian.monumentabaernecessities.api.enums.Tiers;
import com.tristian.monumentabaernecessities.api.events.DrawItemInSlotCallback;
import com.tristian.monumentabaernecessities.api.features.Feature;
import com.tristian.monumentabaernecessities.utils.ItemColors;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.Keyboard;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.ItemStack;
import org.lwjgl.glfw.GLFW;

/**
 * Show rarity as a letter while rendering an item.
 */
public class AbbreviateRarity extends Feature {

    static KeyBinding binding;

    @Override
    public void init() {
        binding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.monumenta-baer-necessities.abbreviateRarity",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_CONTROL,
                "category.monumenta-baer-necessities"
        ));
        DrawItemInSlotCallback.EVENT.register(this::onDrawItemInSlot);
    }


    private void onDrawItemInSlot(DrawContext context, TextRenderer textRenderer, ItemStack stack, int x, int y, String countOverride) {
        if (stack.isEmpty()) return;
        if (MonumentaBaerNecessities.mc.currentScreen == null) return; // this feature only works in the inventory.
        if (!InputUtil.isKeyPressed(MonumentaBaerNecessities.mc.getWindow().getHandle(), KeyBindingHelper.getBoundKeyOf(binding).getCode())) return;
        MonumentaItem.of(stack.getNbt()).flatMap(MonumentaItem::getTier).ifPresent(tier -> {
            String text = String.valueOf(tier.getJsonValue().charAt(0));
            context.getMatrices().translate(0.0F, 0.0F, 200.0F);
            context.drawText(textRenderer, text, x + 6 - textRenderer.getWidth(text), y + 1, ItemColors.getColorForTier(tier.getJsonValue()), true);
        });

    }
}
