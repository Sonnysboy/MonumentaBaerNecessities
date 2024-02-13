package com.tristian.monumentabaernecessities.utils;

import net.minecraft.item.ItemStack;

public class CharmUtils {

    public static boolean isZenithCharm(ItemStack item) {
        return NbtUtils.getMonumentaDataFromItem(item).map(x -> x.getString("Tier").equals("zenithcharm")).orElse(false);
    }

}
