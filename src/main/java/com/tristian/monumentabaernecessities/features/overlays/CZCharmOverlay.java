package com.tristian.monumentabaernecessities.features.overlays;

import com.tristian.monumentabaernecessities.MonumentaBaerNecessities;
import com.tristian.monumentabaernecessities.api.Items;
import com.tristian.monumentabaernecessities.api.features.Feature;
import com.tristian.monumentabaernecessities.items.ZenithCharm;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class CZCharmOverlay extends Feature {


    public void init() {

        ItemTooltipCallback.EVENT.register(this::onItemTooltip);
    }

    private void onItemTooltip(ItemStack stack, TooltipContext context, List<Text> lines) {

        if (!MonumentaBaerNecessities.options.showCzRolls) return;

        final var charm = ZenithCharm.fromItem(stack);
        if (charm.isEmpty()) return; // not a charm

        final var rolls = charm.get().getData().getRolls();

        if (MonumentaBaerNecessities.options.debugOptionsEnabled) {
            if (MinecraftClient.getInstance().player == null) return;
            MinecraftClient.getInstance().player.sendMessage(Text.of(charm.get().toString()));
            MinecraftClient.getInstance().player.sendMessage(Text.of(rolls.toString()));
        }

        final var idx = new AtomicInteger(6);
        for (; idx.get() < lines.size(); idx.incrementAndGet()) {
            var t = lines.get(idx.get());
            rolls.forEach((name, value) -> {
                if (Objects.requireNonNull(Formatting.strip(t.getString())).contains(name)) {
                    lines.set(idx.get(), t.copy().append(String.format(" [%.2f%%]", 100 * value)));
                }
            });
        }
    }

}
