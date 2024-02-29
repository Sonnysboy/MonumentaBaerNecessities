package com.tristian.monumentabaernecessities.api.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;

/**
 * @see com.tristian.monumentabaernecessities.mixin.DrawContextMixin
 */
public interface DrawItemInSlotCallback {

    Event<DrawItemInSlotCallback> EVENT = EventFactory.createArrayBacked(DrawItemInSlotCallback.class,
            listeners -> (DrawContext context, TextRenderer textRenderer, ItemStack stack, int x, int y, String countOverride) ->  {
                for (DrawItemInSlotCallback listener : listeners) {
                    listener.onDrawItemInSlot(context, textRenderer, stack, x, y, countOverride);
                }
            });
    void onDrawItemInSlot(DrawContext context, TextRenderer textRenderer, ItemStack stack, int x, int y, String countOverride);
}
