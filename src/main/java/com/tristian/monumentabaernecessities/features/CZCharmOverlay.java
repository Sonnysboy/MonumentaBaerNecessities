package com.tristian.monumentabaernecessities.features;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import com.tristian.monumentabaernecessities.utils.*;
import net.minecraft.util.Formatting;

public class CZCharmOverlay {


    public static void onItemTooltip(ItemStack stack, TooltipContext context, List<Text> lines) {
//        TODO
        lines.add(Text.of("test"));
        Optional<NbtCompound> data = NbtUtils.getMonumentaDataFromItem(stack);
        if (data.isEmpty()) return;
        Optional<String> tier = data.flatMap(x -> NbtUtils.getString(x, "Tier").describeConstable());
        Optional<Boolean> alreadyDisplayed = data.flatMap(x -> Optional.of(NbtUtils.getBoolean(x, "czrollsdisplayed")));

        if (alreadyDisplayed.get()) return;
        if (tier.isEmpty() || !(tier.get().equals("zenithcharm"))) return;
        final var zenithNbt = data.flatMap(x -> NbtUtils.getCompound(x, "PlayerModified"));
        Optional<List<String>> lore = NbtUtils.getLore(stack);
        if (lore.isEmpty()) return;
        if (zenithNbt.isEmpty()) return;
        var unboxedZenithNbt = zenithNbt.get();
        List<String> loreUnboxed = lore.get();
        System.out.println(unboxedZenithNbt);
        for (int i = 6; i < loreUnboxed.size(); i++) {

            var current = loreUnboxed.get(i);
            var effect = Objects.requireNonNull(Formatting.strip(current)).replaceAll(".+? ", "");
            System.out.println(effect);
            if (unboxedZenithNbt.contains(effect)) {
                System.out.println("has");
            }

        }





    }
}
