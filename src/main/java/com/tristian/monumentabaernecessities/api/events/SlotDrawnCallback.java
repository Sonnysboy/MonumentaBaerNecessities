package com.tristian.monumentabaernecessities.api.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

/**
 * @see com.tristian.monumentabaernecessities.mixin.DrawContextMixin
 */
public interface SlotDrawnCallback {

    Event<SlotDrawnCallback> EVENT = EventFactory.createArrayBacked(SlotDrawnCallback.class,
            listeners -> (DrawContext context, Slot slot) ->  {
                for (SlotDrawnCallback listener : listeners) {
                    listener.onSlotDrawn(context, slot);
                }
            });
    void onSlotDrawn(DrawContext context, Slot slot);
}
