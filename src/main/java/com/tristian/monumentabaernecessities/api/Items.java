package com.tristian.monumentabaernecessities.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.tristian.monumentabaernecessities.MonumentaBaerNecessities;
import net.minecraft.client.MinecraftClient;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.text.Text;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.tristian.monumentabaernecessities.MonumentaBaerNecessities.LOGGER;

public class Items {


    private static final Set<MonumentaItem> items;

    //    basically everything's really FUCKING SLOW.
    private static final HashMap<NbtCompound, MonumentaItem> nbts;


    static void addItem(MonumentaItem item) {
        items.add(item);
        try {
            NbtCompound nbt = StringNbtReader.parse(item.getNbt()).getCompound("Monumenta");
            if (nbt == null) {
                System.out.println(item + " MONUMENTA NBT IS NULL");
            }
//            nbt.remove("Damage"); // you can go fuck yourself
            nbts.put(nbt, item);
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }


    }


    public static Set<MonumentaItem> getAllItems() {
        return items;
    }


    // memoized
    private static final HashMap<NbtCompound, Optional<MonumentaItem>> memo = new HashMap<>();
    public static Optional<MonumentaItem> fromNbt(NbtCompound compound) {

        if (compound == null) return Optional.empty();
        if (memo.containsKey(compound)) return memo.get(compound);
        NbtCompound replaced = compound.copy();
        NbtCompound monumenta = replaced.getCompound("Monumenta");
        monumenta.remove("PlayerModified");
        Optional<MonumentaItem> result;
        memo.put(compound, result = Optional.ofNullable(nbts.get(monumenta)));
        return result;
    }

    static {
        items = new HashSet<>();
        nbts = new HashMap<>();

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

}
