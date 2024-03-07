package com.tristian.monumentabaernecessities.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.nbt.NbtCompound;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.tristian.monumentabaernecessities.MonumentaBaerNecessities.LOGGER;

public class Items {


    private static final Set<MonumentaItem> items;

    //    basically everything's really FUCKING SLOW.
    private static final HashMap<NbtCompound, MonumentaItem> nbts;


    static void addItem(MonumentaItem item) {
        items.add(item);
        NbtCompound nbt = item.getWrappedNbt().getCompound("Monumenta");
        if (nbt == null) {
            System.out.println(item + " MONUMENTA NBT IS NULL");
        }
//            nbt.remove("Damage"); // you can go fuck yourself
        nbts.put(nbt, item);


    }


    public static Set<MonumentaItem> getAllItems() {
        return items;
    }


    // memoized
    private static final HashMap<NbtCompound, Optional<MonumentaItem>> memo = new HashMap<>();

    protected static Optional<MonumentaItem> fromNbt(NbtCompound compound) {

        if (compound == null) return Optional.empty();
        if (memo.containsKey(compound)) return memo.get(compound);
        NbtCompound replaced = compound.copy();
        NbtCompound monumenta = replaced.getCompound("Monumenta");
        monumenta.remove("PlayerModified");
        Optional<MonumentaItem> result;
        memo.put(compound, result = Optional.ofNullable(nbts.get(monumenta)));
        return result;
    }
    public static void load() {

        LOGGER.info("Fetching items...");
        String res = ApiRequests.fetchResponse();
        LOGGER.info("Done fetching items.");
        Gson g = new Gson();
        JsonObject o = g.fromJson(res, JsonObject.class);
        o.asMap().forEach((k, v) -> addItem(ItemParser.decode(k, v.getAsJsonObject())));
        LOGGER.info("Successfully loaded " + items.size() + " items!");
    }
    static {
        items = new HashSet<>();
        nbts = new HashMap<>();

    }

}
