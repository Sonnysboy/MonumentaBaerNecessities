package com.tristian.monumentabaernecessities.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// ive done too much with MONADS!
public class NbtUtils {


    public static Optional<NbtCompound> getNbt(ItemStack item) {
        return Optional.ofNullable(item.getNbt());
    }

    public static Optional<NbtCompound> getCompound(NbtCompound compound, String tag) {
        return Optional.ofNullable(compound.getCompound(tag));
    }
    public static Optional<NbtCompound> getCompound(ItemStack item, String tag) {
        return getNbt(item).flatMap(x -> getCompound(x, tag));
    }
    public static Optional<NbtCompound> getMonumentaDataFromItem(ItemStack item) {
        return getCompound(item, "Monumenta");
    }

//    todo hmmm
    public static Optional<List<String>> getLore(ItemStack item) {
        return getNbt(item).map(y -> getCompound(y, "display")).map(x -> {
            List<String> list = new ArrayList<>();
            for (NbtElement nbtElement : (x.get().getList("Lore", 8))) {
                String string = nbtElement.asString();
                list.add(string);
            }
            return list;
        });
    }
}
