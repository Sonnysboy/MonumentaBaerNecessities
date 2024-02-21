package com.tristian.monumentabaernecessities.mixin;

import com.tristian.monumentabaernecessities.features.inventory.ColorByWoolDungeon;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HandledScreen.class)
public class HandledScreenMixin {


    @Inject(method = "drawSlot", at = @At("HEAD"))
    private void drawSlot(DrawContext context, Slot slot, CallbackInfo ci) {
        ColorByWoolDungeon.onSlotDrawn(context, slot);
    }


}
