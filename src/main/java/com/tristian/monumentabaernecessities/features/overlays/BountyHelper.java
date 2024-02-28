package com.tristian.monumentabaernecessities.features.overlays;

import com.google.gson.JsonElement;
import com.tristian.monumentabaernecessities.utils.NbtUtils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static com.tristian.monumentabaernecessities.MonumentaBaerNecessities.pois;

/**
 * Displays what town your bounty is closest to in the tooltip and the HUD
 */
public class BountyHelper {

    public static void onItemTooltip(ItemStack stack, TooltipContext context, List<Text> lines) {
        lift(() -> pois.get(Formatting.strip(stack.getName().getString())))
                .map(JsonElement::getAsJsonObject).ifPresent(poi -> {
                    NbtUtils.getNbt(stack)
                            .map(nbt -> nbt.getCompound("plain"))
                            .map(nbt -> nbt.getCompound("display"))
                            .map(nbt -> nbt.getList("Lore", 8))
                            .ifPresent(lore -> {
                                // todo add the tier here
                                var coordinates = poi.get("coordinates").getAsJsonObject();
                                var x = coordinates.get("x").getAsString();
                                var y = coordinates.get("y").getAsString();
                                var z = coordinates.get("z").getAsString();
                                var nearest = poi.getAsJsonObject("Nearest");
                                var location = nearest.get("Location").getAsString(); // this is the name
                                var distance = nearest.get("Distance").getAsString();
                                var spawners = nearest.get("Spawners").getAsString();
                                lines.add(Text.of(String.format("This poi is located at (%s,%s,%s)", x, y, z)));
                                lines.add(Text.of(distance + " blocks away from " + location));
                                lines.add(Text.of(spawners + " spawners to destroy."));
                            });
                });
    }

    private static <T> Optional<T> lift(Supplier<T> e) {
        try {
            return Optional.ofNullable(e.get());
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
