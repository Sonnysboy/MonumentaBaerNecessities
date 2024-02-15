package com.tristian.monumentabaernecessities.features.inventory;

import com.tristian.monumentabaernecessities.MonumentaBaerNecessities;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Outline an item in an inventory if it's from a wool dungeon.
 */
public class ColorByWoolDungeon {

    public static void onSlotDrawn(DrawContext context, Slot slot) {
        if (!MonumentaBaerNecessities.options.inventoryWoolItemOutlines)  return;
    }


}
